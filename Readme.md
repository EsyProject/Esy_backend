# Documentation API 🧠 -  Bosch Esy

BaseUrl: `http://172.28.69.4:6967`

## Event 🧧 :

### POST event:

Endpoint: `/event`

Type: Multipartform

Attributes:

```
nameOfEvent: String
responsivle_area: String
access_event: String
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

### *GET My Events:

Endpoint: `/event/myEvent`

Return:

~~~json
{
	"event_id": "Long",
	"nameOfEvent": "String",
	"initialDate": "Date",
	"initialTime": "Time",
	"local": "String",
	"responsible_are": "String"
}
~~~


### GET Event feed:

Endpoint: `/event/feed`

Return:

~~~json
{
	"event_id": "Long",
	"nameOfEvent": "String",
	"initialDate": "Date",
	"initialTime": "Time",
	"finishTime": "Time",
	"description": "String",
	"local": "Time",
	"responsible_area": "String",
	"imgUrl": "List<String> Url"
}
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

Endpoint: `/ticket`

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
	"ticket_id": "Long",
	"initialDate": "Date",
	"finishDate": "Date",
	"initialTime": "Time",
	"finishTime": "Time",
	"date_created": "LocalDate",
	"timeCreated": "LocalTime",
	"qrCodeNumber": "Integer"
}
~~~
