package blind75;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Video {
  String content;
  int views;
  int likes;
  int dislikes;

  public Video(String content) {
    this.content = content;
    this.views = 0;
    this.likes = 0;
    this.dislikes = 0;
  }
}


public class VideoSharingPlatform {

  private Map<Integer, Video> videos; // Stores video objects with their IDs as keys
  private TreeSet<Integer> availableIds;
    // Stores IDs that have been removed and are available for reuse
  private int nextId; // Tracks the next available ID for new videos

  public VideoSharingPlatform() {
    videos = new HashMap<>();
    availableIds = new TreeSet<>();
    nextId = 0;
  }

  /**
   * Uploads a new video and returns its ID.
   *
   * @param video The content of the video.
   * @return The ID of the uploaded video.
   */
  public int upload(String video) {
    int id = availableIds.isEmpty()
      ? nextId++
      : availableIds.pollFirst(); // Reuse a removed ID if available, otherwise use the next sequential ID
    videos.put(id, new Video(video)); // Create a new blind75.Video object and store it in the map
    return id;
  }

  /**
   * Removes a video with the given ID.
   *
   * @param videoId The ID of the video to remove.
   */
  public void remove(int videoId) {
    if (videos.containsKey(videoId)) {
      videos.remove(videoId); // Remove the video from the map
      availableIds.add(videoId); // Add the ID to the set of available IDs for reuse
    }
  }

  /**
   * Simulates watching a video and returns the specified segment of the video content.
   *
   * @param videoId The ID of the video to watch.
   * @param startMinute The starting minute of the segment.
   * @param endMinute The ending minute of the segment.
   * @return The segment of the video content, or "-1" if the video does not exist.
   */
  public String watch(int videoId, int startMinute, int endMinute) {
    if (!videos.containsKey(videoId))
      return "-1";
    Video video = videos.get(videoId);
    video.views++; // Increment the view count
    return video.content.substring(startMinute, Math.min(endMinute + 1,
      video.content.length())); // Return the specified segment of the content
  }

  /**
   * Likes a video with the given ID.
   *
   * @param videoId The ID of the video to like.
   */
  public void like(int videoId) {
    if (videos.containsKey(videoId))
      videos.get(videoId).likes++; // Increment the like count
  }

  /**
   * Dislikes a video with the given ID.
   *
   * @param videoId The ID of the video to dislike.
   */
  public void dislike(int videoId) {
    if (videos.containsKey(videoId))
      videos.get(videoId).dislikes++; // Increment the dislike count
  }

  /**
   * Returns the like and dislike counts of a video with the given ID.
   *
   * @param videoId The ID of the video.
   * @return An array containing the like and dislike counts, or {-1} if the video does not exist.
   */
  public int[] getLikesAndDislikes(int videoId) {
    return videos.containsKey(videoId) ?
      new int[] {videos.get(videoId).likes, videos.get(videoId).dislikes} :
      new int[] {-1};
  }

  /**
   * Returns the view count of a video with the given ID.
   *
   * @param videoId The ID of the video.
   * @return The view count, or -1 if the video does not exist.
   */
  public int getViews(int videoId) {
    return videos.containsKey(videoId) ? videos.get(videoId).views : -1;
  }
}
