const API_RECETAS_URL = "http://localhost:8080/api/recetas";
const API_PACIENTES_URL = "http://localhost:8080/api/pacientes";

let filaEditando = null;

document.addEventListener("DOMContentLoaded", async () => {
  await fetchPacientes();
  fetchRecetas();
});

// Fetch de pacientes para llenar el select
async function fetchPacientes() {
  try {
    const response = await fetch(API_PACIENTES_URL);
    if (!response.ok) throw new Error("Error al cargar pacientes");
    const pacientes = await response.json();

    const selectPaciente = document.getElementById("paciente");
    selectPaciente.innerHTML = '<option value="">Seleccione un paciente</option>';

    pacientes.forEach((paciente) => {
      const option = document.createElement("option");
      option.value = `${paciente.nombre} ${paciente.apellido}`; // Ahora el valor es el texto completo
      option.textContent = `${paciente.nombre} ${paciente.apellido}`;
      selectPaciente.appendChild(option);
    });
  } catch (error) {
    console.error("Error:", error);
  }
}

// Fetch de recetas para mostrar en la tabla
async function fetchRecetas() {
  try {
    const response = await fetch(API_RECETAS_URL);
    if (!response.ok) throw new Error("Error al cargar recetas");
    const recetas = await response.json();
    actualizarTablaRecetas(recetas);
  } catch (error) {
    console.error("Error:", error);
  }
}

// Actualizar la tabla de recetas
function actualizarTablaRecetas(recetas) {
  const tablaRecetas = document.getElementById("tablaRecetas");
  tablaRecetas.innerHTML = "";

  recetas.forEach((receta) => {
    const fila = document.createElement("tr");
    fila.innerHTML = `
            <td>${receta.fecha}</td>
            <td>${receta.nombreDoctor}</td>
            <td>${receta.paciente || "Sin asignar"}</td>
            <td>${receta.medicamentos}</td>
            <td>${receta.sugerencias}</td>
            <td>
                <button class="icon-button btn-editar">
                    <i class="fas fa-edit"></i>
                </button>
                <button class="icon-button btn-eliminar">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        `;

    fila.querySelector(".btn-editar").addEventListener("click", () => editarReceta(fila, receta));
    fila.querySelector(".btn-eliminar").addEventListener("click", () => eliminarReceta(receta.id, fila));

    tablaRecetas.appendChild(fila);
  });
}

// Guardar o actualizar una receta
document.getElementById("guardarReceta").addEventListener("click", async () => {
  const paciente = document.getElementById("paciente").value; // Ahora solo el texto seleccionado

  if (!paciente) {
    alert("Debe seleccionar un paciente.");
    return;
  }

  // Construimos el objeto receta
  const receta = {
    fecha: document.getElementById("fecha").value,
    nombreDoctor: document.getElementById("nombre_del_doctor").value,
    medicamentos: document.getElementById("medicamentos").value,
    sugerencias: document.getElementById("sugerencias").value,
    paciente: paciente, // Asignamos el texto seleccionado del select
  };

  try {
    let response;
    if (filaEditando) {
      response = await fetch(`${API_RECETAS_URL}/${filaEditando.dataset.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(receta),
      });
    } else {
      response = await fetch(API_RECETAS_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(receta),
      });
    }

    if (!response.ok) throw new Error("Error al guardar la receta");
    await fetchRecetas();

    // Resetear formulario y cerrar modal
    document.getElementById("formReceta").reset();
    const modal = bootstrap.Modal.getInstance(document.getElementById("registrarRecetaModal"));
    modal.hide();
  } catch (error) {
    console.error("Error:", error);
  }
});

// Editar una receta
function editarReceta(fila, receta) {
  filaEditando = fila;
  filaEditando.dataset.id = receta.id;

  document.getElementById("fecha").value = receta.fecha;
  document.getElementById("nombre_del_doctor").value = receta.nombreDoctor;
  document.getElementById("paciente").value = receta.paciente || ""; // Cargamos el texto del paciente
  document.getElementById("medicamentos").value = receta.medicamentos;
  document.getElementById("sugerencias").value = receta.sugerencias;

  const modal = new bootstrap.Modal(document.getElementById("registrarRecetaModal"));
  modal.show();
}

// Eliminar una receta
async function eliminarReceta(id, fila) {
  try {
    const response = await fetch(`${API_RECETAS_URL}/${id}`, { method: "DELETE" });
    if (!response.ok) throw new Error("Error al eliminar la receta");

    fila.remove();
  } catch (error) {
    console.error("Error:", error);
  }
}
