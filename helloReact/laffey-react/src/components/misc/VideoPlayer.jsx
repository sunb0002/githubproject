import React, { useCallback } from "react";
import ShakaPlayer from "shaka-player-react";
import "shaka-player/dist/controls.css";

export const VideoPlayer = () => {
  const src =
    "https://lmtesteastus-usea.streaming.media.azure.net/d9f3f0e6-bf17-4e81-86a5-53dad6666599/0fe22d8a-0ee9-4351-95e3-1fecce7b.ism/manifest(format=mpd-time-csf)";
  // "https://storage.googleapis.com/shaka-demo-assets/angel-one-hls/hls.m3u8";

  const caption = "/transcript.vtt";
  // "https://lmtesteastus.blob.core.windows.net/asset-2196e81b-d492-452c-abe3-f2ee3dbb6b21/transcript.vtt";

  const playerRef = useCallback((node) => {
    if (node != null && node.player != null) {
      const { player, videoElement } = node;
      videoElement.muted = true; // Must be muted before setting autoplay

      player.load(src).then(() => {
        player.addTextTrack(caption, "en", "caption", "text/vtt");
        player.setTextTrackVisibility(true);
        videoElement.play(); // autoplay
      });
    }
  }, []);

  return (
    <div className="mx-auto max-w-5xl max-h-40">
      <ShakaPlayer ref={playerRef} />
    </div>
  );
};

