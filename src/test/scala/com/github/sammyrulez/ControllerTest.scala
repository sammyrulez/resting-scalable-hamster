package com.github.sammyrulez

import akka.actor.ActorSystem
import com.github.sammyrulez.models.Example
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO
import org.bson.types.ObjectId
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike

import com.github.SalatConfig.ctx


@RunWith(classOf[JUnitRunner])
class ControllerTest extends ScalatraSuite with FunSuiteLike {
  // `HelloWorldServlet` is your app which extends ScalatraServlet
  val mongoHost = "127.0.0.1"
  private val actorSystem: ActorSystem = ActorSystem("MySpec")
  private val mongoDb: MongoDB = MongoClient(mongoHost, 27017).getDB("test")
  object RestDAO extends SalatDAO[Example, ObjectId](collection = mongoDb("test_collection"))

  RestDAO.insert(new Example(new ObjectId,false,"me","myself"))

  val  indexController = new IndexController(actorSystem,  mongoDb)

  addServlet(indexController, "/api/example")

  test("simple get list ") {
    get("/api/example") {
      status should equal (200)
      println(body)
      body should include ("\"name\":\"myself\"")
    }
  }

  protected override def afterAll(): Unit = {
    super.afterAll()
    actorSystem.shutdown()
  }
}
