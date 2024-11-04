document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('productModal');
    const modalContainer = document.getElementById('modal-container');
    const spanClose = document.querySelector('.modal .close');
    const openModalBtn = document.getElementById('openModalBtn');
    const submitBtn = document.querySelector('#submitBtn');
    openModalBtn.addEventListener('click', openModal);

    submitBtn.addEventListener('click', async (event) => {
      event.preventDefault();
      submitProduct();
    });
  
    // Función para abrir el modal
    function openModal() {
      modal.style.display = 'block';
    }
  
    // Función para cerrar el modal
    function closeModal() {
      modal.style.display = 'none';
    }
  
    // Cerrar el modal al hacer clic en el botón de cerrar
    spanClose.addEventListener('click', closeModal);
  
    // Función para obtener productos desde la API y mostrarlos en el modal
    async function fetchProducts() {
      try {
        const response = await fetch('http://localhost:8080/api/v1/productos');
        if (!response.ok) {
          throw new Error('Error al obtener los productos');
        }
  
        const data = await response.json();
        const products = data;
  
        if (products && products.length > 0) {
          renderProducts(products);
        } else {
          modalContainer.innerHTML = '<p>No se encontraron productos.</p>';
        }
      } catch (error) {
        console.error('Error:', error);
        modalContainer.innerHTML = '<p>No se pudieron cargar los productos.</p>';
      }
    }

    

    async function submitProduct() {
      const URL = 'http://localhost:8080/api/v1/productos/admin';

      const productName = document.querySelector('#productName').value;
      const productDescription = document.querySelector('#productDescription').value;
      const productStock = document.querySelector('#productStock').value;
      const category = document.querySelector('#categoria').value;

      const productData = {
        comida: productName,
        stock: productStock,
        descripcion: productDescription,
        categoria_id: category
      }

      try {
        const response = await fetch(URL, {
            method: 'POST', // Usar el método POST
            headers: {
                'Content-Type': 'application/json' // Indicar que el cuerpo es JSON
            },
            body: JSON.stringify(productData) // Convertir el objeto a JSON
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Credenciales incorrectas'); // Manejar el error si la respuesta no es 2xx
        }

        if (response.ok) {
          const data = await response.json(); // Esto ahora funcionará correctamente
          alert('Subida exitosa!!!'); // Accede al mensaje del JSON
          
        console.log(productData);
      }           
    } catch (error) {
        console.error('Error:', error);
        alert("Error: " + error.message); // Mostrar el error al usuario
    }
    }
  
    // Función para renderizar los productos dentro del modal
    function renderProducts(products) {
      modalContainer.innerHTML = ''; // Limpiar antes de renderizar
  
      products.forEach(product => {
        const productContainer = document.createElement('div');
        const productStock = product.stock;
        const productTitle = product.comida;
        const productDescription = product.descripcion;
        const editBtn = document.createElement('button');
        const deleteBtn = document.createElement('button');
        const buttons = document.createElement('div');

        editBtn.innerText = 'Editar';
        deleteBtn.innerHTML= 'Eliminar';

        buttons.innerHTML = `
        <button class="btn" id="edit-btn-${product.idMeal}" style="background-color: darkgrey">EDITAR</button>
        <button class="btn" id="delete-btn-${product.idMeal}" style="background-color: tomato">ELIMINAR</button>
        `;

        productContainer.innerHTML = `
        <h5>Stock:<br/><p style="color: firebrick";>${productStock}</p></h5>
        <h5>Producto:<br/><p style="color: skyblue";>${productTitle}</p></h5>
        <h5>Descripción:<br/><p style="color: gray";>${productDescription}</p></h5>
        `;

        productContainer.appendChild(buttons);

        productContainer.classList.add('product-list');

  
        modalContainer.appendChild(productContainer);
      });
    }
  
    // Llamar a la función para obtener productos
    fetchProducts();
});