package com.nakos.soa_uas_vincentardyanputra_2101658344.model

import org.simpleframework.xml.*

@Root(name = "rss", strict = false)
data class NyTimesItem(
    @field:Element(name = "channel") var channel: NyTimesChannelItem? = null
)

@Root(name = "channel", strict = false)
data class NyTimesChannelItem(
    @field:Element(name = "title") var title: String? = null,
    @field:ElementList(
        entry = "link",
        inline = true,
        required = false
    ) var links: List<Link>? = null,
    @field:Element(name = "lastBuildDate") var lastBuildDate: String? = null,
    @field:Element(name = "pubDate") var pubDate: String? = null,
    @field:Element(name = "image") var image: NyTimesImage? = null,
    @field:ElementList(
        entry = "item",
        inline = true,
        required = false
    ) var item: List<NyTimesItemList>? = null
)

@Root(name = "image", strict = false)
data class NyTimesImage(
    @field:Element(name = "title") var title: String? = null,
    @field:Element(name = "url") var url: String? = null,
    @field:Element(name = "link") var link: String? = null
)

@Root(name = "item", strict = false)
data class NyTimesItemList(
    @field:Element(name = "title") var title: String? = null,
    @field:ElementList(
        entry = "link",
        inline = true,
        required = false
    ) var links: List<Link>? = null,
    @field:ElementList(
        entry = "description",
        inline = true,
        required = false
    ) var description: List<Descriptions>? = null,
    @field:Element(name = "pubDate") var pubDate: String? = null
)

data class Descriptions @JvmOverloads constructor(
    @field:Text(required = false) var description: String = "",
    @field:Attribute(name = "description", required = false) var descriptionMedia: String = ""
)

data class Link @JvmOverloads constructor(
    // for <link>http://www.somelink.com/</link>
    @field:Text(required = false) var link: String = "",

    // for <atom:link rel="self" href="http://www.someotherlink.com" />
    @field:Attribute(required = false) var rel: String = "",
    @field:Attribute(required = false, name = "type") var contentType: String = "",
    @field:Attribute(required = false) var href: String = ""
)