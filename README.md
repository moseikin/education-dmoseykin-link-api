#### Пример работы с графовой БД neo4j
- mongoId уникально для всех ModelNode, у ClassNode свой уникальный mongoId
- Через /class отношения устанавливаются при сохранении, если есть вложенная нода
- Через /model отношения устанавливаются вызовом createRelationShip(String modelMongoId, String classMongoId)
- ClassNode и ModelNode могут иметь только одну связь (входящую и исходящую) соответственно
- Если одна из пары нод имеет связь, то вызов метода createRelationShip никакого эффекта не даст
- Примеры запросов в [ModelRequests.http](ModelRequests.http) и [ClassRequests.http](ClassRequests.http)