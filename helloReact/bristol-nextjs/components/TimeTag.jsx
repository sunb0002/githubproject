import { format } from "date-fns";
import React from "react";

// date-fns / dayjs are smaller and faster than Moment.js
// https://my.oschina.net/whutzl/blog/3173773
const TimeTag = () => {
    const dateString = format(new Date(), "G yyyy-MMMM-dd HH:mm:ss");
    return <time>{dateString}</time>;
};

export default TimeTag;
