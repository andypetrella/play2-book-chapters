class Twitter
  constructor: ($) ->
    @pane = $("#twitter-pane")
    @title = @pane.find("h3")
    @list = @pane.find("ul")
    @close = @pane.find(".close")

    @close.on "click", () =>
      @title.empty()
      @list.empty()
      @pane.hide()

    $(document).on "click", ".mention", (event) =>
      @search(event, playRouter.controllers.Twitter.mentioning, "Tweets that mentions : ")

    $(document).on "click", ".tag", (event) =>
      @search(event, playRouter.controllers.Twitter.searchTag, "Tweets tagged with : ")

  search: (event, route, title) =>
    val = $(event.currentTarget).html()
    route(val[1..]).ajax(
      success: (data) =>
        @title.html(title+val)
        @list.empty()
        data.forEach (t) =>
          @list.append($("<li>" + t.user + " >> " + t.tweet + "</li>"))

        @pane.show()
    )

window.Twitter = Twitter