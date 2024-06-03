# Documentation API 🧠 -  Bosch Esy

## Getting Started:
**For generate the .jar of application**
~~~shell
mvn package -DskipTests
~~~
**For create a containers of application**
```
docker-compose up
```
**On wls run the following command to get docker eth0**
~~~shell
ip addr show eth0 | grep -oP '(?<=inet\s)\d+(\.\d+){3}'
~~~~
**On powershell as admin run the following command to allow the application accessible on ip address**
~~~shell
netsh interface portproxy add v4tov4 listenport=8765 listenaddress=0.0.0.0 connectport=8765 connectaddress=<dockerEth0>
~~~
<br>

BaseUrl: `http://10.234.89.187:6968`

## Event 🧧 :

### POST event:

Endpoint: `/event`

Type: Multipartform

Attributes:

```
nameOfEvent: String
responsivle_area: String
description: String
images: file (Optional)
localEvent: String
initialDate: Date
finishDate: Date
initialTime: Time
finishTime: Time
```

Return:

~~~json
{
  "event_id": "Long",
  "nameOfEvent": "String",
  "responsible_area": "String",
  "description": "String",
  "imgUrl": "List<String> url",
  "localEvent": "String",
  "initialDate": "Date",
  "finishDate": "Date",
  "initialTime": "Time",
  "finishTime": "Time",
  "date_created": "LocalDate",
  "time_created": "LocalTime",
  "author": "String"
}
~~~

### Get All events:

Endpoint: `/event/events`

Return:

~~~json
[
  {
    "event_id": "Long",
    "nameOfEvent": "String",
    "responsible_area": "String",
    "access_event": "String",
    "description": "String",
    "localEvent": "String",
    "initialDate": "Date",
    "finishDate": "Date",
    "initialTime": "Time",
    "finishTime":"Time",
    "imgUrl": "List<String> url"
  }
]
~~~

### GET Find by ID:

Endpoint: `/event/{event_id}`

Return:

~~~json
{
  "event_id": "Long",
  "nameOfEvent": "String",
  "responsible_area": "String",
  "access_event": "String",
  "description": "String",
  "imgUrl": "List<String> url",
  "localEvent": "String",
  "initialDate": "Date",
  "finishDate": "Date",
  "initialTime": "Time",
  "finishTime": "Time",
  "date_created": "LocalDate",
  "time_created": "LocalTime",
  "author": "String"
}
~~~

### GET Event name:

Endpoint: `/event/name`

Return:

~~~json
{
  "event_id": "Long",
  "nameOfEvent": "String"
}
~~~

### GET My Events:

Endpoint: `/event/myEvents`

Return:

~~~json
[
  {
    "event_id": "Long",
    "nameOfEvent": "String",
    "initialDate": "Date",
    "initialTime": "Time",
    "local": "String",
    "responsible_are": "String"
  }
]
~~~

### GET Card event:

Endpoint: `/event/card`

Return:

~~~json
{
  "event_id": "Long",
  "initialDate": "Date",
  "nameOfEvent": "String",
  "description": "String"
}
~~~

### PUT Update event:

Endpoint: `/event/update/{event_id}`

Attributes:
```
responsible_area: String
description: String
localEvent: String
initialDate: Date
finishDate: Date
initialTime: Time
finishTime: Time
```

~~~json
{
  "event_id": "Long",
  "nameOfEvent": "String",
  "responsible_area": "String",
  "description": "String",
  "imgUrl": "List<String>",
  "localEvent": "String",
  "initialDate": "Date",
  "finishDate": "Date",
  "initialTime": "Time",
  "finishTime": "Time",
  "date_created": "Date",
  "time_created": "Date",
  "author": "String"
}
~~~

## Assessment ⭐ :

### POST Assessment:

Endpoint: `/assessment/{event_id}`

Type: JSON

```
suggestion: String
description_comment: String
highlightPoint: String
assessment: Integer
```

Return:

~~~json
{
  "assessment_id": "Long",
  "author": "String",
  "suggestion": "String",
  "description_comment": "String",
  "assessment": "Integer",
  "hour": "LocalTime",
  "date_created": "LocalDate",
  "highlightPoint": "String"
}
~~~

### GET Assessment with Event

Endpoint: `/assessment/assessments/{event_id}`

Return:

~~~json
{
  "event_id": "Long",
  "nameOfEvent": "String",
  "responsible_area": "String",
  "access_event": "String",
  "description": "String",
  "imgUrl": "List<String> Url",
  "localEvent": "String",
  "initialDate": "Date",
  "finishDate": "Date",
  "initialTime": "Time",
  "finishTime": "Time",
  "assessments": "List<Assessment>"
}
~~~

Assessment return:

~~~json
{
  "assessment_id": "Long",
  "date_created": "Local Date",
  "hour": "Local Time",
  "author": "String",
  "description_comment": "String",
  "suggestion": "String",
  "highlightPoint": "String",
  "assessment": "Integer"
}
~~~

## Comments Event 🗨:

### Get All comments by ID event:

Endpoint: `/assessment/comments/{event_id}`

Return:

~~~json
[
  {
    "comment_id": "Long",
    "author": "String",
    "description_comment": "String",
    "date_created": "Date",
    "assessment": "Integer"
  }
]
~~~


## Dashboard Event 🎯 :

### GET Average Of Event:

Endpoint: `/dashboard/average/{event_id}`

Return:

~~~json
{
  "average": "Integer"
}
~~~

### GET HighPointsGraph:

Endpoint: `/dashboard/highPoints/{event_id}`

Return:

~~~json
{
  "event_id": "Long",
  "food": "Integer",
  "topics_addressed": "Integer",
  "punctuality": "Integer",
  "social_interactions": "Integer"
}
~~~

### GET suggestions based in Assessment:

Endpoint: `/dashboard/suggestion/{event_id}`

Rerturn:

~~~json
[
  {
    "id": "Long",
    "date_suggestion": "Date",
    "message_suggestion": "String"
  }
]
~~~

## Ticket Event 🎫 :

### POST Ticket:

Endpoint: `/ticket/{event_id}`

Type: JSON

Attributes:

```
initialDateTicket: Date,
finishDateTicket: Date,
initialTimeTicket: Time,
finishTimeTicket: Time
```

Return:

~~~json
{
  "initialDateTicket": "Date",
  "finishDateTicket": "Date",
  "initialTimeTicket": "Time",
  "finishTimeTicket": "Time",
  "authorTicket": "String"
}
~~~

### POST (GET ticket) ticket:

Endpoint: `/ticket/getTicket/{event_id}`

Return:

~~~json
{
  "ticket_id": "Long",
  "event_name": "String",
  "qrCodeNumber": "Integer",
  "author": "String",
  "date_created": "Date",
  "timeCreated": "Time"
}
~~~

### PATCH Ticket Image:

Endpoint: `/ticket/{event_id}/{ticket_id}`

Attributes:
```
   images: List<String>
```

Return:

~~~json
{
  "ticket_id": "Long",
  "imageUrl": "List<String>",
  "author": "String",
  "date_created": "Date",
  "time_created": "Time"
}
~~~

### PATCH Confirm ticket:

Endpoint: `/ticket/{event_id/{ticket_id}/confirm`

Return:

~~~json
{
  "ticket_id": "Long",
  "author": "String",
  "isPresence": true,
  "date_created": "Date",
  "time_created": "Time"
}
~~~

### GET Ticket by User:

Endpoint: `/ticket/myTickets`

Return:

~~~json
[
  {
    "initial_date": "Date",
    "nameOfEvent": "String",
    "qrCodeTicket": "String",
    "responsible_area": "String",
    "imageUrl": "String"
  }
]
~~~