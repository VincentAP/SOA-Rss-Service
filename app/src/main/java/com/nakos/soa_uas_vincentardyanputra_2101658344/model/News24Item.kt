package com.nakos.soa_uas_vincentardyanputra_2101658344.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class News24Item(
    @field:Element(name = "channel") var channel: News24ChannelItem? = null
)

@Root(name = "channel", strict = false)
data class News24ChannelItem(
    @field:Element(name = "title") var title: String? = null,
    @field:Element(name = "link") var link: String? = null,
    @field:Element(name = "generator") var generator: String? = null,
    @field:Element(name = "docs") var docs: String? = null,
    @field:Element(name = "lastBuildDate") var lastBuildDate: String? = null,
    @field:Element(name = "pubDate") var pubDate: String? = null,
    @field:ElementList(
        entry = "item",
        inline = true,
        required = false
    ) var item: List<News24ItemList>? = null
)

@Root(name = "item", strict = false)
data class News24ItemList(
    @field:Element(name = "title") var title: String? = null,
    @field:Element(name = "description") var description: String? = null,
    @field:Element(name = "link") var link: String? = null,
    @field:Element(name = "pubDate") var pubDate: String? = null
)