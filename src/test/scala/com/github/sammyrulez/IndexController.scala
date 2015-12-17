package com.github.sammyrulez

import akka.actor.ActorSystem
import com.gatillc.rest_api.RestController
import com.github.sammyrulez.models.Example
import com.mongodb.casbah.MongoDB

class IndexController(system:ActorSystem, mongoDB:MongoDB)
    extends RestController[Example](system, mongoDB, "test_collection") {

}

