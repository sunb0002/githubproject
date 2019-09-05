function GeneralResponse(payload, err) {
    this.payload = payload;
    this.err = err;
}

GeneralResponse.prototype.isOk = function () {
    return this.payload && this.payload !== 0;
}

const ok = (payload) => new GeneralResponse(payload);
const error = (err) => new GeneralResponse("", err);

module.exports = {
    ok,
    error
};