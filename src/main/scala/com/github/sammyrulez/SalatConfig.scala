package com.github

import com.novus.salat.json._
import com.novus.salat.{Context, StringTypeHintStrategy, TypeHintFrequency}

package object SalatConfig {
  implicit val ctx = new Context {
    val name = "json-test-context"
    override val typeHintStrategy = StringTypeHintStrategy(when = TypeHintFrequency.WhenNecessary,
      typeHint = "_t")
    override val jsonConfig = JSONConfig(
      objectIdStrategy = StringObjectIdStrategy)
  }
}