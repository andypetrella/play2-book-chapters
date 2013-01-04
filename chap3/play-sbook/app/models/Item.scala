package models

import org.joda.time.LocalTime

case class Item(user:String, timestamp:LocalTime, message:String) {}