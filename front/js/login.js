const loginForm = document.getElementById("loginForm");
const errorMessage = document.getElementById("errorMessage");

loginForm.addEventListener("submit", async (event) => {
  event.preventDefault(); 

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    });

    if (response.ok) {
      const data = await response.json();
      const token = data.token;

      localStorage.setItem("jwt", token);

      window.location.href = "/dashboard.html";
    } else {
      errorMessage.textContent =
        "Credenciales inválidas. Inténtalo nuevamente.";
    }
  } catch (error) {
    console.error("Error:", error);
    errorMessage.textContent = "Ocurrió un error. Inténtalo más tarde.";
  }
});

function togglePasswordVisibility() {
  const passwordInput = document.getElementById("password");
  const toggleIcon = document.querySelector(".toggle-password i");  

  if (passwordInput.type === "password") {
    passwordInput.type = "text";
    toggleIcon.classList.remove("bi-eye");      
    toggleIcon.classList.add("bi-eye-slash");  
  } else {
    passwordInput.type = "password";
    toggleIcon.classList.remove("bi-eye-slash"); 
    toggleIcon.classList.add("bi-eye");          
  }
}
