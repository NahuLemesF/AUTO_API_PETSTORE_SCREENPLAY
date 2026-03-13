# language: es
Característica: Gestion del ciclo de vida de usuarios en Petstore

  Escenario: Gestionar exitosamente el ciclo de vida completo de un usuario
    Dado que el administrador de API prepara un usuario para pruebas
    Cuando crea el usuario en Petstore
    Entonces la creacion del usuario es exitosa
    Cuando consulta el usuario creado por username
    Entonces la consulta inicial retorna los datos creados
    Cuando actualiza los datos del usuario
    Entonces la actualizacion del usuario es exitosa
    Cuando consulta nuevamente el usuario actualizado
    Entonces la consulta posterior retorna los datos actualizados
    Cuando elimina el usuario
    Entonces la eliminacion del usuario es exitosa
    Cuando consulta nuevamente el usuario eliminado
    Entonces la API informa que el usuario ya no existe
