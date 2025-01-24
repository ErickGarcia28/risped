const API_URL = "http://localhost:8080/api/pacientes";


document.addEventListener("DOMContentLoaded", fetchPacientes);


document.getElementById("guardarPaciente").addEventListener("click", guardarPaciente);

let filaEditando = null;


async function fetchPacientes() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Error al obtener pacientes");
        const pacientes = await response.json();
        actualizarTabla(pacientes);
    } catch (error) {
        console.error("Error:", error);
    }
}


function actualizarTabla(pacientes) {
    const tablaPacientes = document.getElementById("tablaPacientes");
    tablaPacientes.innerHTML = ""; 

    pacientes.forEach(paciente => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${paciente.nombre}</td>
            <td>${paciente.apellido}</td>
            <td>${paciente.edad}</td>
            <td>${paciente.peso}</td>
            <td>${paciente.altura}</td>
            <td>${paciente.alergias}</td>
            <td>
                <button class="icon-button btn-editar">
                    <i class="fas fa-edit"></i>
                </button>
                <button class="icon-button btn-eliminar">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        `;

        
        fila.querySelector(".btn-editar").addEventListener("click", () => editarPaciente(fila, paciente));
        fila.querySelector(".btn-eliminar").addEventListener("click", () => eliminarPaciente(paciente.id, fila));

        tablaPacientes.appendChild(fila);
    });
}


async function guardarPaciente() {
    const paciente = {
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        edad: parseInt(document.getElementById("edad").value),
        peso: document.getElementById("peso").value,
        altura: document.getElementById("altura").value,
        alergias: document.getElementById("alergias").value
    };

    try {
        let response;
        if (filaEditando) {
            
            response = await fetch(`${API_URL}/${filaEditando.dataset.id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(paciente)
            });
        } else {
            
            response = await fetch(API_URL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(paciente)
            });
        }

        if (!response.ok) throw new Error("Error al guardar el paciente");
        const nuevoPaciente = await response.json();
        
        if (filaEditando) {
            
            filaEditando.innerHTML = `
                <td>${nuevoPaciente.nombre}</td>
                <td>${nuevoPaciente.apellido}</td>
                <td>${nuevoPaciente.edad}</td>
                <td>${nuevoPaciente.peso}</td>
                <td>${nuevoPaciente.altura}</td>
                <td>${nuevoPaciente.alergias}</td>
                <td>
                    <button class="icon-button btn-editar">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="icon-button btn-eliminar">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            `;

            filaEditando.querySelector(".btn-editar").addEventListener("click", () => editarPaciente(filaEditando, nuevoPaciente));
            filaEditando.querySelector(".btn-eliminar").addEventListener("click", () => eliminarPaciente(nuevoPaciente.id, filaEditando));
            filaEditando = null;
        } else {
            
            fetchPacientes();
        }

        
        document.getElementById("formPaciente").reset();
        const modal = bootstrap.Modal.getInstance(document.getElementById("registrarPacienteModal"));
        modal.hide();
    } catch (error) {
        console.error("Error:", error);
    }
}


function editarPaciente(fila, paciente) {
    filaEditando = fila;
    filaEditando.dataset.id = paciente.id;

    document.getElementById("nombre").value = paciente.nombre;
    document.getElementById("apellido").value = paciente.apellido;
    document.getElementById("edad").value = paciente.edad;
    document.getElementById("peso").value = paciente.peso;
    document.getElementById("altura").value = paciente.altura;
    document.getElementById("alergias").value = paciente.alergias;

    const modal = new bootstrap.Modal(document.getElementById("registrarPacienteModal"));
    modal.show();
}


async function eliminarPaciente(id, fila) {
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        if (!response.ok) throw new Error("Error al eliminar el paciente");

        
        location.reload();
    } catch (error) {
        console.error("Error:", error);
    }
}

