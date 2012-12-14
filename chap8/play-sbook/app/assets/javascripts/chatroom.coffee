class ChatRoom
  constructor: (conf)->
    @id = conf.id
    @el = conf.el
    @since = conf.since

  formatTimestamp: (ts) ->
    ts = new Date(ts)
    ts.getHours() + ":" + ts.getMinutes() + ":" + ts.getSeconds() + "." + ts.getMilliseconds()

  updateList: (target, list, format) =>
    c = target.find("ul")
    $(list).each((idx, item) =>
      c.append('<li class="item">'+format(item)+'</li>')
    )

  #format a word 'm' wrapping in a span having the provided class 'cl'
  formatWord: (m, cl) => '<span class="'+cl+'">'+m+'</span>'
  #format a word with a class 'mention'
  formatMention:  (m) => @formatWord(m, "mention")
  #format a word with a class 'tag'
  formatTag:      (m) => @formatWord(m, "tag")
  #shows all items and images
  updateChat: (items, images) =>
    #first update the list of items
    # each item will be pre-processed by splitting the message
    #  the resulting words will be checked against the @ and #
    #  presence. If so there formatted accordingly and then joined back
    @updateList(
      @el.find(".react.past"),
      items,
      (i) =>
        # this is the item formatting function
        #first we split
        words = i.message.split(/\s+/)
        #then we construct a new array with pre-processed words
        message = words.map (w) =>
          if w.charAt(0)=="@"
            @formatMention(w)
          else if w.charAt(0)=="#"
            @formatTag(w)
          else w
        # we join the whole thing back
        message = message.join(' ')
        console.dir(i)
        result  = '<span class="time">[' + @formatTimestamp(i.timestamp)+ ']</span> <span>'+i.user+ '</span> > '
        # we add the pre-processed message
        result += message
    )

    #update the list of images with the provided images array
    @updateList(
      @el.find(".attach.past"),
      images,
      (i) =>
        #this is the images formatting function
        cap = i.caption
        cap+ ' : <img src="/chat/images/'+ i.internalId+ '" alt="'+ cap+ '" title="'+ cap+ '"/>'
    )

window.ChatRoom = ChatRoom