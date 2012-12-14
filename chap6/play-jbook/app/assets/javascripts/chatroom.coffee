class ChatRoom
  constructor: (conf)->
    @id = conf.id
    @el = conf.el
    @since = conf.since

  formatTimestamp: (ts) ->
    ts.values[0] + ":" + ts.values[1] + ":" + ts.values[2] + "." + ts.values[3]

  updateList: (target, list, format) =>
    c = target.find("ul")
    $(list).each((idx, item) =>
      c.append('<li class="item">'+format(item)+'</li>')
    )


  updateChat: (items, images) =>
    @updateList(
      @el.find(".react.past"),
      items,
      (i) =>
        '<span class="time">[' + @formatTimestamp(i.timestamp)+ ']</span> <span>'+i.user.email+ '</span> > '+ i.message
    )

    @updateList(
      @el.find(".attach.past"),
      images,
      (i) =>
        cap = i.caption
        cap+ ' : <img src="/chat/images/'+ i.internalId+ '" alt="'+ cap+ '" title="'+ cap+ '"/>'
    )

window.ChatRoom = ChatRoom