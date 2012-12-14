class Dashboard
  constructor: (opt) ->
    @el = opt.el

    # The div in the root element of the dashboard
    # which has `header` in their classes will toggle
    #   their content (its following element)
    @header = @el.find(".header")
    @header.on "click", () ->
      $(@).next().toggle('slow') #on click toggle the next element
    if (opt.closed)
      @header.eq(0).click();

    # Enable the '+' button to add new select boxes dynamically
    @el.find("#addChatSelector").on "click", () =>
      tmpl = $(".sampleJsBlock.chat")
      count = @el.find("form .selectChat").length
      # take the template html block as text
      #  replace the x's by the current count
      newContent = tmpl.html()
                        .replace(/([\[_])x([\]_])/g, "$1"+count+"$2")
      #put the new content at the end of the according block
      @el.find("#chatSelectors").append(newContent)


    ##  Forms submission logic
    ##

    @interactSelector = @el.find("#chatSelect");
    @chatsRouter = playRouter.controllers.Chats

    #Since a file is involved we need a plugin to do it
    # so this object configures it for our sendImage form
    # >>>  http://jquery.malsup.com/  <<<
    @uploadFormOptions =
      target: '#upload',
      success: (responseText, statusText, xhr, $form) -> console.dir(responseText)
    # send the image and caption asynchronously
    @el.find("#sendImage").submit (e) =>
      $form = $(e.currentTarget)
      chat = @interactSelector.val()
      route = @chatsRouter.receiveImage(chat)
      $form.attr("action", route.url)

      # !!!! USE THE jquery.form plugin !!!!
      $form.ajaxSubmit(@uploadFormOptions)
      false


    # NEW field keeping track of the chats being shown
    @chatIds = undefined

    # NOW: talking consists in pushing a json message in the socket
    @el.find("#talk").submit (e) =>
      $form = $(e.currentTarget)

      chat = @interactSelector.val()
      value = {
        chatId : parseInt(chat)
        message: $form.find("[name=message]").val()
      }
      @socket.send(JSON.stringify(value))

      false


  joinArray: (array, sep) ->
    del = ""
    cs = array.reduce (x, y) ->
      x = x + del + y
      del = sep
      x
    , ""

  now: () -> new Date().getTime()

  opened: (chatIds) =>
    @chatIds = chatIds

    @routeToStream = @chatsRouter.chatsStream(@joinArray(@chatIds, ","), @now())
    @socket = new WebSocket(@routeToStream.webSocketURL())
    @socket.onmessage = (msg) ->
      data = $.parseJSON(msg.data)
      if (data.update)
        rooms = window.chatrum.rooms
        #for comprehension on object with filter
        rooms[c].updateChat(d.items, d.images) for c, d of data when c.match("chat[0-9]+")

window.Dashboard = Dashboard