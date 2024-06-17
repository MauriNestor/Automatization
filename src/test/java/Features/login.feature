Feature: Insertar un curso al carrito de compras

  Scenario: Verificar que al hacer clic en el carrito de compras, redireccione a los cursos añadidos
    Given Ingresar a la pagina "https://campusalalay.pythonanywhere.com/login"
    When Introducir el usuario "mauricio" en username
    And Introducir "Mauricio123" en Contraseña
    And Seleccionar "Estudiante" en rol
    And Click en el boton iniciar sesion
    And Click en icono de carrito
    And Click en Ver Curso
    Then mostrar detalles del curso agregado al carrito
