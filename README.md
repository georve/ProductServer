# ProductServer

Recomendaciones antes de realizar la prueba técnica
 Tomarse el tiempo para leer y analizar el ejercicio antes de resolverlo.
 Enfocarse en que la solución al ejercicio incluya las cuatro preguntas. Los tips son
importantes, pero es más importante resolver el ejercicio.
 Resolver el ejercicio desde el punto de vista de los fundamentos de la programación
orientada a objetos (es decir, menos, es más). Trate de hacer algo funcional, dando
respuesta a las cuatro preguntas y si le queda tiempo trate de mejorar lo que hizo.
 Importante plantear una solución limpia y eficiente (Código limpio).
 Recuerde que no hay una solución correcta, existen varias alternativas, desarrolle la que le
parezca mejor o más conveniente teniendo en cuenta los tiempos.
Tips (temas de la prueba):
1. Java 8
2. Proper unit tests
3. Comment code
4. OOP principles
5. Design patterns
6. Solid principles

# Coding Challenge
We have a bunch of product categories for SquareTrade in which we sell insurance.
Categories include Furniture, Electronics, Home Appliances. Categories have hierarchies.
So Home appliances have sub categories like Major Appliances, Minor Appliances, Lawn &amp;
Garden. And Major Appliances have subcategories of Kitchen Appliances and General
Appliances.
For each category there are zero or more keywords associated with it. This is used by our
search engine to go to the specific category. Example: Lawn &amp; Garden has keywords
&quot;Lawn&quot;, &quot;Garden&quot;, &quot;GardeningTools&quot;. If a certain category is missing keywords, it needs to
inherit it from the parent. Assume that there is a Root Category that is at the highest level
with a default set of keywords.
Questions:
1. Create the data structure with your sample categories
2. Write code that gets keywords for a given category.
3. Write code that gets the &quot;level&quot; of the category starting from the Root.
4. Create test cases for the above. Make your own assumptions about the data
when in doubt.
