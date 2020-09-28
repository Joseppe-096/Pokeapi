# Pokeapi

Configurar la app en AndroidStudio

1.- Abrir AndroidStudio, si es primera vez que abre AndroidStudio le saldrá una ventana la cual tiene varias opciones para seleccionar, 
de click en donde dice "Get from Version Control", le aparecera una segunda ventana.<br>
2.- En la segunda venta le pedira un url copie la url del proyecto de github que se muestra a continuacion "https://github.com/Joseppe-096/Pokeapi.git"
  <br>Nota *Copiar la url sin comillas<br>
3.- Pegue la url en el espacio donde indique la URL la ventana de AndroidStudio.<br>
4.- Una vez pegada la url del github del proyecto se habilitará el botón de clone, de clic al botón de clone, espera que cargue el proceso.<br>
5.- Le pedira si desea abrir el proyecto le da en "Yes" espera que cargue el proyecto esto prodría tardar un poco.<br>
6.- Una vez cargado el proyecto completo podrá ejecutarlo en un emulador de andriod o en un dispositivo movil conectado.<br>
7.- Para ejecutarlo basta con ver el dispositivo android conectado, ir a la pestaña Run, se desplega un submenu, darle clic a Run 'app' o darle clic 
al botón que se encuentra en la barra de herramientas en forma de triangulo de play como en el reproductor de musica.<br>

Ejemplo de la app

![alt tag](https://github.com/Joseppe-096/Pokeapi/blob/master/login.PNG)
<br>
El usuario es: jose@gmail.com y la contraseña es: 12345
<br>
El inicio de la app con la lista de pokemons
<br>
![alt tag](https://github.com/Joseppe-096/Pokeapi/blob/master/Incio.png)
<br>
<h4>
  NOTA*
</h4>
<small>Si desea ver los pokemons guardados </small>
<p>
 Ubiquese dentro del proyecto en la carpeta app -> res -> layout -> activity_main.xml
  abrir el activity_main.xml dando doble clic en el.
  En la interfaz de android aparecera en la parte superior derecha tres opciones Code, Split y Design, le da clic a Split,
    busca el EditText y en las opciones dentro del EditText busca este fragmento de codigo: android:visibility="gone" cambia el gone por la palabra "visible".<br>
    Listo ejecuta la aplicacion y podra ver los pokemons que se guardaron en el dispositivo movil.<br>
    Abrir el url de una imgen como ejemplo: https://github.com/Joseppe-096/Pokeapi/blob/master/mostrarPokemons.PNG
</p>


