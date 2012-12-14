package models;

import org.joda.time.DateTime;

case class Chat(date:DateTime, occurrence:Int, items:Seq[Item])