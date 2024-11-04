document.addEventListener('DOMContentLoaded', () => {

  const userData = localStorage.getItem('userData');
  const URL = `http://localhost:8080/api/v1/usuarios/email/${userData.email}`;

  getUserData();

  async function getUserData() {
    try {
      const response = await fetch(URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json' // Indicar que el cuerpo es JSON
        },
        body: JSON.stringify(userData.email) // Convertir el objeto a JSON
      });

      const data = response.json();
      console.log(data);
    } catch (error) {
      console.error('Error:', error);
      alert("Error: " + error.message); // Mostrar el error al usuario
    }  
  };


  import('./utils/modal.js').then(module => {
    const  { historyModal } = module;
    historyModal();
  });

  import('../components/headerComponent.js');
  import('../components/cardComponent.js');

  // Selecionar los elementos HTML
  const editBtn = document.getElementById('edit-btn');
  const saveBtn = document.getElementById('save-btn');
  const inputs = document.querySelectorAll('input[type="text"]');
  const username = document.querySelector('#username-value');
  const userEmail = document.querySelector('#user-email-value');
  const passwordInput = document.querySelector('input[type="password"]');
  const dniInput = document.querySelector('input[type="number"]');

  let editingEnabled = false;

  function updateCheckboxState() {
    if (!editingEnabled) {
      wordkDays.forEach((day) => {
        day.classList.add('checkbox-disabled');
      });
    } else {
      wordkDays.forEach((day) => {
        day.classList.remove('checkbox-disabled');
      });
    }
  }
  
  function enableEditing() {
    editingEnabled = true;
    inputs.forEach((input) => {
      input.readOnly = false;
      passwordInput.readOnly = false;
      dniInput.readOnly = false;
    });
    saveBtn.style.display = 'block'; // Mostrar el botón de guardar cambios
    updateCheckboxState();
  }

  passwordInput.addEventListener('focus', function() {
    if (editingEnabled) {
      this.type = 'text';
    }
  });
  
  passwordInput.addEventListener('blur', function() {
    this.type = 'password';
  });

  // Función para guardar los cambios
  function saveChanges() {
  // Recopilar los valores de los inputs
  alert('La operación ha culminado con éxito...');
  editingEnabled = false;
  wordkDays.forEach((day) => {
    userData.days[day.value] = day.checked;
  }),
  inputs.forEach((input) => {
      userData[input.id] = input.value;
      userData[passwordInput.id] = passwordInput.value;
      userData[dniInput.id] = dniInput.value;
    }
  );

  // Enviar los datos al servidor o realizar la lógica de negocio aquí
  console.log(userData);

  // Deshabilitar la edición de los inputs
  inputs.forEach((input) => {
      input.readOnly = true;
      passwordInput.readOnly = true;
      passwordInput.type = 'password';
      dniInput.readOnly = true;
      wordkDays.readOnly = true;
      wordkDays.type = 'checkbox';
  });
  saveBtn.style.display = 'none'; // Ocultar el botón de guardar cambios
  }

  // Agregar eventos a los botones
  editBtn.addEventListener('click', enableEditing);
  saveBtn.addEventListener('click', saveChanges);
})