const API = window.API_BASE_URL || "http://localhost:8080/api";
let activeMapTarget = "lost";
let marker;
let currentUser = JSON.parse(localStorage.getItem("petalert_user") || "null");
const qs = id => document.getElementById(id);

function setMessage(text, type = "ok") {
    const box = qs("system-message");
    if (box) {
        box.textContent = text;
        box.className = `message ${type}`;
    }
}

async function request(path, options = {}) {
    const res = await fetch(API + path, {
        headers: { "Content-Type": "application/json" },
        ...options
    });
    if (!res.ok) {
        let error = { error: "No se pudo completar la operación" };
        try { error = await res.json(); } catch { }
        throw new Error(error.error || Object.values(error)[0] || "Error");
    }
    return res.json();
}

function clean(value) {
    return (value || "").trim();
}

function split(value) {
    return clean(value).split(",").map(item => item.trim()).filter(Boolean);
}

function updateSession() {
    const pill = qs("session-pill");
    if (currentUser) {
        const status = currentUser.identityVerified ? "cuenta verificada" : "cuenta sin verificar";
        pill.textContent = `Sesión: ${currentUser.fullName}`;
        if (qs("account-box")) {
            qs("account-box").textContent = `Cuenta activa: ${currentUser.fullName} (${status})`;
        }
    } else {
        pill.textContent = "Sin sesión iniciada";
        if (qs("account-box")) qs("account-box").textContent = "Crea una cuenta o inicia sesión para recibir avisos y contactar cuidadores. Demo: demo@petalert.pe / demo123";
    }
}

function switchTab(id) {
    document.querySelectorAll(".tab-link").forEach(button => button.classList.toggle("active", button.dataset.tab === id));
    document.querySelectorAll(".tab-page").forEach(page => page.classList.toggle("active", page.id === `tab-${id}`));
    setTimeout(() => window.dispatchEvent(new Event("resize")), 80);
}

function initMap() {
    const map = L.map("map").setView([-12.046374, -77.042793], 13);
    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
        maxZoom: 19,
        attribution: "© OpenStreetMap"
    }).addTo(map);

    map.on("click", event => {
        const lat = event.latlng.lat.toFixed(6);
        const lng = event.latlng.lng.toFixed(6);
        marker ? marker.setLatLng(event.latlng) : marker = L.marker(event.latlng).addTo(map);
        setLocation(activeMapTarget, lat, lng);
        qs("map-status").textContent = `Ubicación seleccionada: ${lat}, ${lng}`;
    });

    document.querySelectorAll("[data-target]").forEach(button => button.addEventListener("click", () => {
        activeMapTarget = button.dataset.target;
        qs("map-status").textContent = `El próximo punto se usará en: ${button.textContent}`;
    }));
}

function setLocation(target, lat, lng) {
    const ids = {
        lost: ["lost-lat", "lost-lng"],
        sighting: ["sighting-lat", "sighting-lng"],
        caregiver: ["caregiver-lat", "caregiver-lng"]
    }[target];
    if (ids) {
        qs(ids[0]).value = lat;
        qs(ids[1]).value = lng;
    }
}

async function loadAll() {
    try {
        const viewer = currentUser?.id ? `?viewerUserId=${encodeURIComponent(currentUser.id)}` : "";
        const [reports, caregivers, sightings] = await Promise.all([
            request("/reportes-perdidos"),
            request(`/cuidadores${viewer}`),
            request("/avistamientos")
        ]);
        renderReports(reports);
        renderCaregivers(caregivers);
        renderSightings(sightings);
        renderReportSelect(reports);
        renderCaregiverSelect(caregivers);
        await loadInbox();
        setMessage("Información actualizada.");
    } catch (error) {
        setMessage(error.message, "error");
    }
}

async function loadInbox() {
    const box = qs("inbox-list");
    if (!box) return;
    if (!currentUser) {
        box.innerHTML = '<div class="card">Inicia sesión para ver tus avisos.</div>';
        return;
    }
    try {
        const user = await request(`/usuarios/${currentUser.id}`);
        currentUser = user;
        localStorage.setItem("petalert_user", JSON.stringify(user));
        box.innerHTML = (user.inbox || []).length
            ? user.inbox.map(message => `<div class="card"><strong>Aviso recibido</strong><p>${message}</p></div>`).join("")
            : '<div class="card">Aún no tienes avisos.</div>';
        updateSession();
    } catch {
        box.innerHTML = '<div class="card">No se pudieron cargar los avisos.</div>';
    }
}

function renderReports(items) {
    qs("lost-list").innerHTML = items.length
        ? items.map(report => `<div class="card"><strong>${report.petName}</strong><p>${report.species || ""} ${report.breed || ""} · ${report.color || ""}</p><p>${report.description || ""}</p><small>ID: ${report.id}</small><br><small>Referencia: ${report.location?.addressLabel || "Sin referencia"}</small><br><small>Avisos enviados: ${report.notificationsSent || 0}</small></div>`).join("")
        : '<div class="card">No hay reportes activos.</div>';
}

function renderReportSelect(items) {
    const select = qs("sighting-report-id");
    select.innerHTML = '<option value="">No sé cuál es</option>' + items.map(report => `<option value="${report.id}">${report.petName} - ${report.species} ${report.color || ""}</option>`).join("");
}

function renderCaregivers(items) {
    qs("caregiver-list").innerHTML = items.length
        ? items.map(caregiver => {
            const contact = caregiver.contactVisible
                ? `<small>Teléfono: ${caregiver.contactPhone || "No indicado"}</small><br><small>Correo: ${caregiver.contactEmail || "No indicado"}</small>`
                : `<small>${caregiver.contactMessage || "Inicia sesión con una cuenta verificada para ver el contacto."}</small>`;
            return `<div class="card"><strong>${caregiver.fullName}</strong><p>${(caregiver.role || "").replaceAll("_", " ")}</p><p>${(caregiver.restrictions?.acceptedSpecies || []).join(", ")} · ${(caregiver.restrictions?.acceptedSizes || []).join(", ")}</p><small>Calificación: ${(caregiver.averageRating || 0).toFixed(1)} / 5</small><br><small>Alertas: ${caregiver.receivesLostPetAlerts ? "activadas" : "desactivadas"}</small><br>${contact}</div>`;
        }).join("")
        : '<div class="card">No hay cuidadores registrados.</div>';
}

function renderCaregiverSelect(items) {
    const select = qs("review-caregiver");
    select.innerHTML = items.map(caregiver => `<option value="${caregiver.id}">${caregiver.fullName}</option>`).join("");
}

function renderSightings(items) {
    qs("sighting-list").innerHTML = items.length
        ? items.map(sighting => `<div class="card"><strong>${sighting.species || "Avistamiento"}</strong><p>${sighting.breed || ""} · ${sighting.color || ""}</p><p>${sighting.notes || ""}</p><small>Reporte: ${sighting.reportId || "No indicado"}</small><br><small>Contacto voluntario: ${sighting.finderContact || "No indicado"}</small><br><small>Referencia: ${sighting.location?.addressLabel || "Sin referencia"}</small></div>`).join("")
        : '<div class="card">No hay avistamientos registrados.</div>';
}

function renderSearchResults(results) {
    qs("search-results").innerHTML = results.length
        ? results.map(result => {
            const organization = result.organizationName ? `<small>${result.organizationType || "Entidad"}: ${result.organizationName}</small><br>` : "";
            const contact = result.contactPhone || result.contactEmail
                ? `<small>Contacto: ${result.contactName || "Responsable"} · ${result.contactPhone || ""} ${result.contactEmail || ""}</small><br>`
                : "";
            return `<div class="card"><strong>${result.title}</strong><p>${result.species || ""} ${result.breed || ""} · ${result.color || ""}</p><p>${result.detail || ""}</p>${organization}${contact}<small>${result.contactPolicy || ""}</small></div>`;
        }).join("")
        : '<div class="card">No se encontraron resultados.</div>';
}

function bindForms() {
    document.querySelectorAll(".tab-link").forEach(button => button.addEventListener("click", () => switchTab(button.dataset.tab)));

    qs("register-form").addEventListener("submit", async event => {
        event.preventDefault();
        try {
            currentUser = await request("/usuarios/registro", {
                method: "POST",
                body: JSON.stringify({
                    fullName: clean(qs("reg-name").value),
                    email: clean(qs("reg-email").value),
                    phone: clean(qs("reg-phone").value),
                    identityDocument: clean(qs("reg-dni").value),
                    password: qs("reg-password").value
                })
            });
            localStorage.setItem("petalert_user", JSON.stringify(currentUser));
            updateSession();
            setMessage("Cuenta creada e iniciada.");
            await loadAll();
            switchTab("perdido");
        } catch (error) {
            setMessage(error.message, "error");
        }
    });

    qs("login-form").addEventListener("submit", async event => {
        event.preventDefault();
        try {
            currentUser = await request("/usuarios/login", {
                method: "POST",
                body: JSON.stringify({
                    email: clean(qs("login-email").value),
                    password: qs("login-password").value
                })
            });
            localStorage.setItem("petalert_user", JSON.stringify(currentUser));
            updateSession();
            setMessage("Sesión iniciada.");
            await loadAll();
        } catch (error) {
            setMessage(error.message, "error");
        }
    });

    qs("logout").addEventListener("click", async () => {
        currentUser = null;
        localStorage.removeItem("petalert_user");
        updateSession();
        await loadAll();
    });

    qs("lost-form").addEventListener("submit", async event => {
        event.preventDefault();
        if (!currentUser) {
            setMessage("Primero inicia sesión para registrar una mascota perdida.", "error");
            switchTab("cuenta");
            return;
        }
        try {
            await request("/reportes-perdidos", {
                method: "POST",
                body: JSON.stringify({
                    petName: clean(qs("lost-name").value),
                    species: qs("lost-species").value,
                    breed: clean(qs("lost-breed").value),
                    color: clean(qs("lost-color").value),
                    description: clean(qs("lost-description").value),
                    ownerName: currentUser.fullName,
                    ownerContact: currentUser.phone || currentUser.email,
                    ownerUserId: currentUser.id,
                    alertRadiusKm: Number(qs("lost-radius").value || 1),
                    location: {
                        latitude: Number(qs("lost-lat").value),
                        longitude: Number(qs("lost-lng").value),
                        addressLabel: clean(qs("lost-address").value)
                    }
                })
            });
            event.target.reset();
            setMessage("Reporte guardado.");
            await loadAll();
            switchTab("panel");
        } catch (error) {
            setMessage(error.message, "error");
        }
    });

    qs("sighting-form").addEventListener("submit", async event => {
        event.preventDefault();
        try {
            await request("/avistamientos", {
                method: "POST",
                body: JSON.stringify({
                    reportId: qs("sighting-report-id").value || null,
                    species: qs("sighting-species").value,
                    breed: clean(qs("sighting-breed").value),
                    color: clean(qs("sighting-color").value),
                    notes: clean(qs("sighting-notes").value),
                    finderContact: clean(qs("sighting-contact").value),
                    location: {
                        latitude: Number(qs("sighting-lat").value),
                        longitude: Number(qs("sighting-lng").value),
                        addressLabel: clean(qs("sighting-address").value)
                    }
                })
            });
            event.target.reset();
            setMessage("Avistamiento registrado. Si estaba asociado a un reporte, el dueño recibirá el aviso.");
            await loadAll();
            switchTab("panel");
        } catch (error) {
            setMessage(error.message, "error");
        }
    });

    qs("search-form").addEventListener("submit", async event => {
        event.preventDefault();
        try {
            const data = await request("/busqueda", {
                method: "POST",
                body: JSON.stringify({
                    intent: qs("search-intent").value,
                    species: qs("search-species").value || null,
                    breed: clean(qs("search-breed").value),
                    color: clean(qs("search-color").value)
                })
            });
            renderSearchResults(data.results);
        } catch (error) {
            setMessage(error.message, "error");
        }
    });

    qs("caregiver-form").addEventListener("submit", async event => {
        event.preventDefault();
        try {
            await request("/cuidadores", {
                method: "POST",
                body: JSON.stringify({
                    fullName: clean(qs("caregiver-name").value),
                    role: qs("caregiver-role").value,
                    identityDocument: clean(qs("caregiver-dni").value),
                    contactPhone: clean(qs("caregiver-phone").value),
                    contactEmail: clean(qs("caregiver-email").value),
                    acceptedSpecies: split(qs("caregiver-species").value),
                    acceptedSizes: split(qs("caregiver-sizes").value),
                    medicationAllowed: qs("caregiver-meds").checked,
                    receivesLostPetAlerts: qs("caregiver-alerts").checked,
                    location: {
                        latitude: Number(qs("caregiver-lat").value),
                        longitude: Number(qs("caregiver-lng").value),
                        addressLabel: clean(qs("caregiver-address").value)
                    }
                })
            });
            setMessage("Cuidador registrado.");
            await loadAll();
        } catch (error) {
            setMessage(error.message, "error");
        }
    });

    qs("review-form").addEventListener("submit", async event => {
        event.preventDefault();
        try {
            await request(`/cuidadores/${qs("review-caregiver").value}/resenas`, {
                method: "POST",
                body: JSON.stringify({
                    ownerName: clean(qs("review-owner").value),
                    rating: Number(qs("review-rating").value),
                    comment: clean(qs("review-comment").value)
                })
            });
            setMessage("Reseña guardada.");
            await loadAll();
        } catch (error) {
            setMessage(error.message, "error");
        }
    });

    qs("refresh").addEventListener("click", loadAll);
}

document.addEventListener("DOMContentLoaded", () => {
    initMap();
    bindForms();
    updateSession();
    loadAll();
});
