document.addEventListener('DOMContentLoaded', () => {
  const navigateTo = sessionStorage.getItem('navigateTo'); // Leer la selección
  const loginSection = document.getElementById('login-section');
  const registerSection = document.getElementById('register-section');

  
  import('../components/headerComponent.js');

  if (navigateTo === 'login') {
    loginSection.classList.add('fade-in');
    loginSection.removeAttribute('hidden');
    registerSection.setAttribute('hidden', 'true');
  } else if (navigateTo === 'register') {
    registerSection.classList.add('fade-in');
    registerSection.removeAttribute('hidden');
    loginSection.setAttribute('hidden', 'true');
  }

  // Limpia el valor después de usarlo, si deseas que se reinicie
  sessionStorage.removeItem('navigateTo');

  const registerA = document.getElementById('register-a');
  const loginA = document.getElementById('login-a');

  registerA.addEventListener('click', () => {
    loginSection.classList.add('fade-in');
    loginSection.removeAttribute('hidden');
    registerSection.setAttribute('hidden', 'true');
  })

  loginA.addEventListener('click', () => {
    registerSection.classList.add('fade-in');
    registerSection.removeAttribute('hidden');
    loginSection.setAttribute('hidden', 'true');
  })
  
  const LogInBtn = document.querySelector('#login-button');

  LogInBtn.addEventListener('click', async (event) => {
    event.preventDefault();
    UserLogIn();
  });

  async function UserLogIn() {
      const email = document.querySelector('#login-email').value; // Obtener el valor del campo de email
      const password = document.querySelector('#login-password').value; // Obtener el valor del campo de contraseña
      const URL = 'http://localhost:8080/api/v1/usuarios/login'; // URL del endpoint de login

      // Crear el objeto que se enviará en el cuerpo de la solicitud
      const userData = {
          email: email,
          contrasena: password
      };

      try {
          const response = await fetch(URL, {
              method: 'POST', // Usar el método POST
              headers: {
                  'Content-Type': 'application/json' // Indicar que el cuerpo es JSON
              },
              body: JSON.stringify(userData) // Convertir el objeto a JSON
          });

          if (!response.ok) {
              const errorData = await response.json();
              throw new Error(errorData.message || 'Credenciales incorrectas'); // Manejar el error si la respuesta no es 2xx
          }

          if (response.ok) {
            const data = await response.json(); // Esto ahora funcionará correctamente
            localStorage.setItem('userData', userData);
            alert('Login exitoso: ' + data.message); // Accede al mensaje del JSON
            window.location.assign('/index.html');
        }           
      } catch (error) {
          console.error('Error:', error);
          alert("Error: " + error.message); // Mostrar el error al usuario
      }
  }

  const RegisterBtn = document.querySelector('#login-button');

  RegisterInBtn.addEventListener('click', async (event) => {
    event.preventDefault();
    UserRegister();
  });

  async function UserRegister() {
      const email = document.querySelector('#register-email').value; // Obtener el valor del campo de email
      const password = document.querySelector('#register-password').value; // Obtener el valor del campo de contraseña
      const confirmPassword = document.querySelector('#confirm-password').value;
      const name = document.querySelector('#register-name').value;
      const dni = document.querySelector('#register-dni').value;
      const URL = 'http://localhost:8080/api/v1/usuarios/register'; // URL del endpoint de login

      // Crear el objeto que se enviará en el cuerpo de la solicitud
      const userData = {
          email: email,
          contrasena: password,
          nombre: name,
          dni: dni
      };

      if (confirmPassword === password){
        try {
          const response = await fetch(URL, {
            method: 'POST', // Usar el método POST
              headers: {
                  'Content-Type': 'application/json' // Indicar que el cuerpo es JSON
              },
              body: JSON.stringify(userData) // Convertir el objeto a JSON
          });

          if (!response.ok) {
              const errorData = await response.json();
              throw new Error(errorData.message || 'Credenciales incorrectas'); // Manejar el error si la respuesta no es 2xx
          }

          if (response.ok) {
            const data = await response.json(); // Esto ahora funcionará correctamente
            localStorage.setItem('userData', userData);
            alert('Registro exitoso: ' + data.message); // Accede al mensaje del JSON
            window.location.assign('/index.html');
        }           
      } catch (error) {
          console.error('Error:', error);
          alert("Error: " + error.message); // Mostrar el error al usuario
      }
    };
  }
});
