# Bid flight

Proyecto para la asignatura de Arquitectura de software de la Escuela colombiana de ingeniería Julio Garavito.

--- 
### Problema a solucionar

Se suele presentar el caso de que en algunos vuelos quedan asientos vacíos ya sea por cancelación o por llegadas tarde por parte del cliente, o no se lograron vender suficientes asientos para llenar el avión, es por ello que actualmente las aerolíneas venden más boletas que asientos en el avión, en busca de evitar dicha situación, aun así, se basa en modelos predictivos que aún no están perfeccionados y se dan casos en los que aún quedan puestos vacíos. 

Las soluciones actuales como despegar.com o skyscanner.com cuentan con funciones en el concepto de "last minute flights" cuyo objetivo es llenar el avión de alguna manera cuando esta pronto a despegar, sin embargo, tiene la desventaja que al ser enfocada a vuelos en general y se pierde el enfoque de estos vuelos de último momento, lo cual tiende a confundir al usuario por la falta de una clara exposición de la información. 

---
### Descripción

Nuestra aplicación busca proveer este servicio de "last minute flights" con la particularidad de que las aerolíneas tienen la oportunidad de subastar estos asientos vacíos, para que los usuarios tengan la oportunidad de acceder a dichos vuelos, el objetivo es que dichas subastas inicien con una puja baja para que sea atractiva para el usuario.

Nuestra aplicación busca que en temporadas altas al haber alto flujo de usuarios en busca de vuelos las pujas suban y generen beneficios a las aerolíneas mientras le da a la oportunidad a los usuarios de obtener los vuelos que tanto desean, sin embargo no solo beneficia a las aerolíneas en términos financieros pues en temporadas bajas se espera que las pujas se mantengan bajas y los usuario puedan conseguir los vuelos más baratos que lo usual.


---
### Arquitectura

En este caso nuestra aplicación se encuentra diseñada para funcionar en base al framework Spring-boot, además de esto, su despliegue se encuentra sobre la plataforma Heroku.

A continuación, mostramos algunos de los modelos usados dentro de esta aplicación.

#### Diagrama de datos
![](img/dbDiagram.PNG)

#### Diagrama de componentes
![](img/componentsDiagram.PNG)

#### Diagrama de despliegue
![](img/deploymentDiagram.PNG)


---
#### Autores
- Michael Preciado 
- Jeymar Vega 