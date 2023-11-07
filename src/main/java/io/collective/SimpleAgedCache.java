package io.collective;

import java.time.Clock;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;

// A simple cache implementation that stores objects with a time-to-live (TTL).
public class SimpleAgedCache {

    // A thread-safe map to store cache entries. Each entry has an expiry time.
    private final ConcurrentHashMap<Object, CacheEntry> cache = new ConcurrentHashMap<>();
    // Clock instance to allow for time manipulation, useful for testing.
    private final Clock clock;

    // Constructor that takes a Clock instance for time-based operations.
    public SimpleAgedCache(Clock clock) {
        this.clock = clock;
    }

    // Default constructor that uses the system default time zone clock.
    public SimpleAgedCache() {
        this(Clock.systemDefaultZone());
    }

    // Method to add an object to the cache with a specified TTL.
    public void put(Object key, Object value, int retentionInMillis) {
        // Calculate the expiry time based on the current time and the given TTL.
        Instant expiryTime = Instant.now(clock).plusMillis(retentionInMillis);
        // Store the object and its expiry time in the cache.
        cache.put(key, new CacheEntry(value, expiryTime));
    }

    // Method to retrieve an object from the cache.
    public Object get(Object key) {
        // Get the cache entry.
        CacheEntry entry = cache.get(key);
        // Check if the entry is not null and not expired.
        if (entry != null && !entry.isExpired(clock)) {
            // Return the value if it's still valid.
            return entry.value;
        }
        // If the entry is expired, remove it from the cache.
        cache.remove(key);
        // Return null if the entry is expired or not found.
        return null;
    }

    // Method to check if the cache is empty.
    public boolean isEmpty() {
        // Clean up expired entries before checking.
        cleanUp();
        return cache.isEmpty();
    }

    // Method to get the number of entries in the cache.
    public int size() {
        // Clean up expired entries before getting the size.
        cleanUp();
        return cache.size();
    }

    // Helper method to remove expired entries from the cache.
    private void cleanUp() {
        // Iterate over the entries of the cache.
        Iterator<ConcurrentHashMap.Entry<Object, CacheEntry>> it = cache.entrySet().iterator();
        while (it.hasNext()) {
            ConcurrentHashMap.Entry<Object, CacheEntry> entry = it.next();
            // If an entry is expired, remove it.
            if (entry.getValue().isExpired(clock)) {
                it.remove();
            }
        }
    }

    // Inner class representing a cache entry with a value and an expiry time.
    private static class CacheEntry {
        final Object value;
        final Instant expiryTime;

        // Constructor for the cache entry.
        CacheEntry(Object value, Instant expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        // Method to check if the cache entry is expired.
        boolean isExpired(Clock clock) {
            // Compare the current time with the expiry time.
            return Instant.now(clock).isAfter(expiryTime);
        }
    }
}
