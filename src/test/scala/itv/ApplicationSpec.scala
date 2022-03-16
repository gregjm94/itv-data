package itv
import itv.Application.findConcurrent

import org.scalatest.funsuite.AnyFunSuite




class ApplicationSpec extends AnyFunSuite {
  /*
    Test data - Some rows with start and end times that overlap to build up how many concurrent
    viewing sessions there are. All on the same day.

    2022-01-01T10:35:40.108Z, 2022-01-01T13:33:40.108Z
    2022-01-01T10:36:40.108Z, 2022-01-01T14:34:40.108Z
    2022-01-01T11:35:40.108Z, 2022-01-01T11:40:40.108Z
    2022-01-01T11:38:40.108Z, 2022-01-01T13:32:40.108Z
    2022-01-01T15:38:40.108Z, 2022-01-01T17:42:40.108Z

    There should be 3 concurrent sessions here

   */
  val session1: VideoPlay = VideoPlay(java.time.Instant.parse("2022-01-01T10:35:41.167Z"), java.time.Instant.parse("2022-01-01T13:33:40.108Z"))
  val session2: VideoPlay = VideoPlay(java.time.Instant.parse("2022-01-01T10:36:40.108Z"), java.time.Instant.parse("2022-01-01T14:34:40.108Z"))
  val session3: VideoPlay = VideoPlay(java.time.Instant.parse("2022-01-01T10:35:41.167Z"), java.time.Instant.parse("2022-01-01T10:31:40.108Z"))
  val session4: VideoPlay = VideoPlay(java.time.Instant.parse("2022-01-01T11:35:40.108Z"), java.time.Instant.parse("2022-01-01T13:32:40.108Z"))
  val session5: VideoPlay = VideoPlay(java.time.Instant.parse("2022-01-01T15:38:40.108Z"), java.time.Instant.parse("2022-01-01T17:42:40.108Z"))
  // Ends nanosecond before session 1
  val session6: VideoPlay = VideoPlay(java.time.Instant.parse("2022-01-01T10:35:41.167Z"), java.time.Instant.parse("2022-01-01T10:35:41.167Z").minusNanos(1))

  val normalCollection: List[VideoPlay] = List(session1, session2, session3, session4, session5)

  val expectedCount = 3

  test("A full set should return max concurrent views in the data set") {
    assert(findConcurrent(normalCollection) == expectedCount)
  }

  val edgeCollection: List[VideoPlay] = List(session1, session6, session2)
  val edgeExpectedCount = 2
  test("testing edge case with java instant") {
    assert(findConcurrent(edgeCollection) == edgeExpectedCount)
  }
}
