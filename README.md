# :hibiscus: Flora-mastered :hibiscus: 

Esta applicación funciona con un cliente rest creado con laravel en la nube. La app consiste en poder almacenar datos sobre distintas floras pudiendo añadir nuevas floras,
editarlas y añadirle imagenes representativas a esas floras. La applicación se actualiza automaticamente accediendo a la base de datos en la nube donde se guardan todos los datos
de las floras como su nombre, habitat, amenazas, biotipo, entre muchas otras  además también se almacenan las imagenes.


# Layouts :bouquet:

# Main Activity

Cuenta con un Recycler View en el que se mostrarán todas las Floras que hayan sido añadidas con algunos de sus campos. En principio solo podemos ver el nombre, la familia
y la imagen de la flora pero con el botón de expansion que tiene cada item del recycler view podremos ver algunos detalles más.

Ademas en esta actividad organizamos toda la navegación. 
* Botón :heavy_plus_sign: : lleva a la actividad de añadir floras
* Botón :camera: : lleva a la actividad de añadir imagen
* Pulsar un item del Recycler View : lleva a la actividad de editar Flora

![WhatsApp Image 2022-03-04 at 22 29 44 (2)](https://user-images.githubusercontent.com/81224742/156845476-395c08fa-2ea9-42e2-b8d0-cde6b841a0f8.jpeg)

![WhatsApp Image 2022-03-04 at 22 29 44 (3)](https://user-images.githubusercontent.com/81224742/156845475-d7eedc0d-5df8-4965-8b5d-2dcbbfaa135b.jpeg)



# Add Flora Activity :heavy_plus_sign:

Añadiremos flora en esta actividad, el único campo obligatorio es el nombre. podemos cancelar la acción si lo deseamos 


![WhatsApp Image 2022-03-04 at 22 29 44](https://user-images.githubusercontent.com/81224742/156845480-e66ed748-07eb-4989-80be-87cfd156270e.jpeg)

![WhatsApp Image 2022-03-04 at 22 29 44 (1)](https://user-images.githubusercontent.com/81224742/156845478-bb7c9abb-435d-4df1-8be7-9cb7b8cb53cf.jpeg)





# Edit Flora Activity :pencil:

Aqui editamos la flora seleccionada, en primera instancia los campos no seran editables y simplemente podremos ver todos los datos de esa flora, esta 
actividad funciona como edición y borrado al mismo tiempo si pulsamos borrar nos avisará de que el borrado será permanente.
Por otro lado al pulsar el botón editar los campos se volveran editables y la imagen se ocultará para más comodidad. Una vez editado como queramos simplemente 
dandole al botón guardar la flora cambiará sus datos.

![WhatsApp Image 2022-03-04 at 22 29 44 (4)](https://user-images.githubusercontent.com/81224742/156845472-87c75448-9fd6-4247-accb-09e41c31556c.jpeg)

![WhatsApp Image 2022-03-04 at 22 29 44 (5)](https://user-images.githubusercontent.com/81224742/156845485-6c310712-3f25-44d7-add6-a039005f5561.jpeg)

# Add Imagen Activity :camera:
 
 Con el spinner, seleccionamos a la flora a la que le añadiremos la imagen y pulsando la imagen iremos al gestor de archivos para seleccionar una foto. 
 Esta cambiará automáticamente para ver la previsualización y le podremos poner un nombre y descripción con la que se guardará en la base de datos


![WhatsApp Image 2022-03-04 at 22 29 44 (6)](https://user-images.githubusercontent.com/81224742/156845483-9f51243d-048c-44b2-ba30-4f0525a365f3.jpeg)
![Screenshot_1646429883](https://user-images.githubusercontent.com/81224742/156845339-25986262-cdaf-4948-bc46-eab4bd4a836b.png)
![Screenshot_1646429888](https://user-images.githubusercontent.com/81224742/156845348-94ca0a98-4770-4263-8bf4-4ae8cb238f3a.png)
![Screenshot_1646429924](https://user-images.githubusercontent.com/81224742/156845350-521308b7-60c4-4149-9b99-45496b72282f.png)





* **Daniel Moreno** - *Repositorio:* - [Flora](https://informatica.ieszaidinvergeles.org:10012/)
