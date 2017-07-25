package com.quaildev.redis

import com.redis.serialization.{Format, Parse}
import com.redis.{RedisClient, RedisClientPool}
import com.twitter.chill.KryoPool
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FunSuite}

class ScalaRedisCacheTest extends FunSuite with BeforeAndAfter with MockFactory {

  private var mockKryoPool: KryoPool = _
  private var mockRedisClient:RedisClient = _
  private var cache: ScalaRedisCache = _

  before {
    mockRedisClient = stub[RedisClient]
    mockKryoPool = stub[KryoPool]

    val redisClientPool = new RedisClientPool("", 0) {
      override def withClient[T](body: (RedisClient) => T): T = body(mockRedisClient)
    }

    cache = new ScalaRedisCache(redisClientPool, mockKryoPool)
  }

  test("getAll issues Redis mget for all keys") {
    val cacheKeys = Seq("first", "second", "third")

    cache.getAll(cacheKeys)

    (mockRedisClient.mget(_:String, _:Seq[String])(_:Format, _:Parse[String]))
      .verify(cacheKeys.head, cacheKeys.tail, *, *)
  }

}
