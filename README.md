# Final-EspecializacionBackend
Explicación de la implementación del patrón Circuit Breaker:

En nuestro sistema, aplicamos el patrón de diseño Circuit Breaker con el propósito de mejorar la resiliencia y disponibilidad del microservicio 'catalog-service'. Debido a su relevancia en nuestra arquitectura, este microservicio establece conexiones con otros componentes, incluyendo los microservicios de series y películas.

La implementación del patrón se llevó a cabo en los métodos saveMovie y saveSerie, respectivamente. En el momento en que se efectúan 5 peticiones a estos servicios y el 50% de ellas resulta en fallo, el estado del circuito pasa automáticamente a open. Tras 15 segundos, el estado transita a half-open y se verifica nuevamente el estado del servicio.

En cuanto a disponibilidad el patrón nos permite, que cuando un usuario administrador quiera dar de alta una serie o película, y el servicio no se encuentre diponible, se muestre un mensaje indicando esto, así previniendo saturar o sobrecargar aún más el servicio que está fallando.

