package itv

import java.time.Instant

case class VideoPlay(startTime: Instant, endTime: Instant)

object Application extends App {
  /*
    Scala implementation of the python implementation found here:
    https://stackoverflow.com/questions/14092518/finding-the-most-frequent-number-from-a-set-of-ranges

    Takes a List of VideoPlay case classes and returns an Int of the max number of concurrent views

    Algorithm assigns a +1 for a startTime and a -1 for an endTime combines these into a sorted list.
    The sort is required to get the time-based events in order of how they happened. Then it is simply a case of counting
    how many max current views there are (initialised to zero) and comparing it to the previous amount of current viewers there were. If this
    new amount is higher than the previous then it becomes the previous max and continue to iterate.
   */
  def findConcurrent(videoPlayCollection: List[VideoPlay]): Int = {

    // Used to increase and decrease the counter that holds the current number of views
    val startedFlag = 1
    val endedFlag = -1

    val flaggedStarted: List[(Int, Int)] = videoPlayCollection map (x => (x.startTime.toEpochMilli.toInt, startedFlag))
    val flaggedEnded: List[(Int, Int)] = videoPlayCollection map (x => (x.endTime.toEpochMilli.toInt, endedFlag))
    val flaggedCollection: List[(Int, Int)] = flaggedStarted ::: flaggedEnded

    var maxConcurrentViews = 0
    var currentNumberOfViews = 0

    for (views <- flaggedCollection.sorted) {
      currentNumberOfViews += views._2 //
      maxConcurrentViews = maxConcurrentViews.max(currentNumberOfViews)
    }
    maxConcurrentViews
  }
}
