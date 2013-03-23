Minimum migration to 2.1
========================

Goal
----
The goal of this branch is to migrate all sources to the 2.1 version.

This is mostly following the [guidelines](http://www.playframework.com/documentation/2.1.0/Migration).
But also include some adaptation that aren't documented so far.

The purpose here is not to have a pure 2.1 version (some API slightly changed), 
but it's to make all surgical changes necessary to have the code compiling and running.

Also all changes are presented incrementally chapter by chapter as well (so changes made for chapters 1 and 2 must be done for all following).

Structure
---------
The changes focuses on file changes, and is break down into chapters bounds.

So each file correspond to an 3rd level header. 

The lines to be changed starts with '<<' with their related changes, lines starting with '>>'.

Checkpoints (one or several tackled chapters) are indicated within a blockquote and followed by a straight line.

Last note
---------
You can also just `checkout` this branch, if you're starting from 2.1 !


Changes
--------

## build.properties
<< "sbt.version=0.11.3"
>> "sbt.version=0.12.2"

## plugins.sbt
<< "addSbtPlugin("play" % "sbt-plugin" % "2.0.2")""
>> "addSbtPlugin("play" % "sbt-plugin" % "2.1.0")""

> chap1 ok
> chap2 ok

---------------------------------------

-- books.less
<< #27: box-shadow
>> missing ')' before ';'

:: chap3 ok

-- *.java
<< "= form("
>> "= Form.form("

<< ".join("
>> ".fetch("

-- Addres.scala
>> "import anorm.SqlParser._"

:: chap4 ok

-- Chats.java
<< #35,71,93: "fetch"
>> #35,71,93: "join"

:: chap5 ok

-- Chats.java
<< #45,208: "fetch"
>> #45,208: "join"

>> "import scala.concurrent.duration.Duration;"
>>  #195 ",Akka.system().dispatcher()"

-- Chat.java
>> "import java.util.ArrayList"

>> "public List<Item> items;"
<< "public List<Item> items = new ArrayList<Item>();"

>> "public List<Image> images;"
<< "public List<Image> images = new ArrayList<Image>();"

:: chap6 ok

-- Chats.scala
>> "import scala.concurrent.duration.Duration;"
>> "import play.api.libs.concurrent.Execution.Implicits._"

<< "onStart = println("Web Socket started"),"
>> "onStart = () => println("Web Socket started"),"

<< "onComplete = println("Web Socket stopped"),"
>> "onComplete = () => println("Web Socket stopped"),"

-- Twitter.scala
>> "import play.api.libs.concurrent.Execution.Implicits._"

-- Image.scala
<< "def reads (json: JsValue): Image =
      Image(
    "
>> "def reads (json: JsValue): JsResult[Image] =
      JsSuccess(Image(
    "

-- Item.scala
<< "def reads (json: JsValue): Item =
      Item(
    "
>> "def reads (json: JsValue): JsResult[Item] =
      JsSuccess(Item(
    "

:: chap7 ok
:: chap8 ok
:: chap9 ok
