export async function fetchProducts() {
    const productGrid = document.getElementById('products-container');
    const productoDelDiaContainer = document.getElementById('daily-product-container');

    try {
        // URL corregida de la API
        const response = await fetch('http://localhost:8080/api/v1/productos');
        if (!response.ok) {
          throw new Error('Error al obtener los productos');
        }
    
        const products = await response.json(); // Ajustar dependiendo de la estructura de los datos
        console.log(products);
        console.log(products.categoria);
    
        if (products && products.length > 0) {
          // Seleccionar un Producto del Día aleatorio
          const productoDelDia = products[Math.floor(Math.random() * products.length)];
          renderProductoDelDia(productoDelDia);
    
          // Renderizar el resto de productos
          renderProducts(products);
        } else {
          productGrid.innerHTML = '<p>No se encontraron productos.</p>';
        }
      } catch (error) {
        console.error('Error:', error);
        productGrid.innerHTML = '<p>No se pudieron cargar los productos.</p>';
      }
  
      // Función para renderizar el Producto del Día
    function renderProductoDelDia(product) {
      productoDelDiaContainer.innerHTML = ''; // Limpiar el contenedor
    
      const productCard = document.createElement('product-card');
      productCard.setAttribute('stock', product.stock);
      //productCard.setAttribute('image', product.strMealThumb);
      productCard.setAttribute('title', product.comida);
      productCard.setAttribute('type', product.categoria);
      productCard.setAttribute('description', product.descripcion);
      productCard.setAttribute('show-add-button', 'true');
      productCard.setAttribute('show-del-button', 'false');
    
      productoDelDiaContainer.appendChild(productCard);
    }
    
    // Función para renderizar los productos en el grid
    function renderProducts(products) {
      productGrid.innerHTML = ''; // Limpiar el grid antes de renderizar
    
      products.forEach(product => {
        const productCard = document.createElement('product-card');
        productCard.setAttribute('stock', product.stock);
        //productCard.setAttribute('image', product.strMealThumb);
        productCard.setAttribute('title', product.comida);
        productCard.setAttribute('type', product.categoria);
        productCard.setAttribute('description', product.descripcion);
        productCard.setAttribute('show-add-button', 'true');
        productCard.setAttribute('show-del-button', 'false');
    
        productGrid.appendChild(productCard);
      });
    }
};