package com.coffeetaste.model

/**
 * Defines the possible forms of coffee that can be recorded in a tasting entry.
 *
 * - `DRIP_BAG` represents coffee that has been pre ground and packaged into
 *   single‑use drip bags.
 * - `ROASTED_BEANS` represents whole roasted coffee beans requiring grinding
 *   before brewing.
 *
 * Adding new values in the future is as simple as defining additional
 * constants here.
 */
enum class CoffeeType {
    /** Pre‟ground coffee in a single‑serve drip bag. */
    DRIP_BAG,

    /** Whole roasted coffee beans requiring grinding before brewing. */
    ROASTED_BEANS
}
