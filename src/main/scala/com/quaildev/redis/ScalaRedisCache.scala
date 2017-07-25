package com.quaildev.redis

import com.redis.serialization.Parse.Implicits.parseByteArray
import com.redis.{RedisClientPool, Seconds, SecondsOrMillis}
import com.twitter.chill.KryoPool

import scala.util.{Failure, Success, Try}

class ScalaRedisCache(redisClientPool: RedisClientPool, kryo: KryoPool) {

  private[redis] val TTL: SecondsOrMillis = Seconds(600)

  def getAll[T](keys: Iterable[String]): Iterable[Option[T]] = {
    Try {
      redisClientPool.withClient { redisClient =>
        val cacheResults = redisClient.mget(keys.head, keys.tail.toSeq: _*).getOrElse(Nil)
        cacheResults.map { maybeBytes: Option[Array[Byte]] =>
          maybeBytes.map { bytes =>
            kryo.fromBytes(bytes).asInstanceOf[T]
          }
        }
      }
    } match {
      case Success(value) => value
      case Failure(e) => Nil
    }
  }

//  def get[T](key: String): Option[T] = {
//    Try {
//      redisClientPool.withClient { redisClient =>
//        redisClient.get(key).map(bytes => kryo.fromBytes(bytes).asInstanceOf[T])
//      }
//    } match {
//      case Success(value) => value
//      case Failure(e) =>
//        None
//    }
//  }
//
//  def set[T](key: String, value: T): Try[Unit] = {
//    Try {
//      redisClientPool.withClient { redisClient =>
//        redisClient.set(key, kryo.toBytesWithClass(value), onlyIfExists = false, TTL)
//      }
//    }
//  }

}
