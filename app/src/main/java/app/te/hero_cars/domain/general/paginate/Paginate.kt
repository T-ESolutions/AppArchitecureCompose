package app.te.hero_cars.domain.general.paginate

import androidx.annotation.Keep

@Keep
open class Paginate(
     val meta: Meta = Meta(),
     val links: Links = Links(),
)