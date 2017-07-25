Fails with below output when built:

```
[info] Compiling 1 Scala source to C:\git\scalamock-demo\target\scala-2.11\test-classes...
[error] C:\git\scalamock-demo\src\test\scala\com\quaildev\redis\ScalaRedisCacheTest.scala:16: method pipeline overrides nothing.
[error] Note: the super classes of <$anon: com.redis.RedisClient> contain the following, non final members named pipeline:
[error] def pipeline(f: PipelineClient => Any): Option[List[Any]]
[error]     mockRedisClient = stub[RedisClient]
[error]                           ^
[error] C:\git\scalamock-demo\src\test\scala\com\quaildev\redis\ScalaRedisCacheTest.scala:17: object creation impossible, since method newInstance in class ResourcePool of type ()com.twitter.chill.SerDeState is not defined
[error]     mockKryoPool = stub[KryoPool]
[error]                        ^
[error] two errors found
[error] (test:compileIncremental) Compilation failed
[error] Total time: 6 s, completed Jul 25, 2017 2:36:52 PM
```