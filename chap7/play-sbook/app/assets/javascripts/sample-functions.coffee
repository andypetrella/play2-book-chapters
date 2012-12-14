class A
  constructor: (v) ->
    @v = v

  f: (i) ->
      console.log(i)
      console.log(@v)

  g: (i) =>
      console.log(i)
      console.log(@v)

 a = new A("I'm a")
 b = new A("I'm b")

a.f.call(a, "Will print >>  I'm a")
# $> Will print >>  I'm a
# $> I'm a

a.g.call(a, "Will print >>  I'm a")
# $> Will print >>  I'm a
# $> I'm a

a.f.call(b, "Will print >>  I'm b") ##### BANG! ####
# $> Will print >>  I'm b
# $> I'm b

a.g.call(b, "Will print >>  I'm a") # this has been bound to a!
# $> Will print >>  I'm a
# $> I'm a