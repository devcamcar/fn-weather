import fdk
import io
import json
import logging
import sys
import urllib
import urllib.request


API_URL = "http://api.apixu.com/v1/current.json"
API_KEY = "518cb86fff374394bf5211954190103"

# TODO: Configure URL endpoint for Slack Bot.
# https://api.slack.com/bot-users
# https://api.slack.com/events-api#events_api_request_urls


def handler(ctx, data: io.BytesIO=None):
    try:
        body = json.loads(data.getvalue())
        logging.debug(str(body))
        city = body.get("city")
    except (Exception, ValueError) as ex:
        logging.error(str(ex))
        return {"error": "No city name specified"}

    url = "%s?key=%s&q=%s" % (API_URL, API_KEY, urllib.parse.quote_plus(city))

    try:
        with urllib.request.urlopen(url) as response:
            forecast = json.loads(response.read())
    except Exception as e:
        logging.exception("Unable to fetch weather data")
        return {"error": e}

    # Determine how to show the image in Slack.
    message = "%s: Currently %s and %.1f degrees.\n" % (
               forecast["location"]["name"],
               forecast["current"]["condition"]["text"],
               forecast["current"]["temp_f"])

    logging.info(message)

    # TODO: Publish message to the Slack channel.
    # https://github.com/slackapi/python-slackclient

    return {"temp_f": forecast["current"]["temp_f"]}

if __name__ == "__main__":

    # handler(None, data="Los Angeles")
    fdk.handle(handler)

"""
   {
      "location": {
         "name":"Paris",
         "region":"Ile-de-France",
         "country":"France",
         "lat":48.87,
         "lon":2.33,
         "tz_id":"Europe/Paris",
         "localtime_epoch":1551475803,
         "localtime":"2019-03-01 22:30"
       },
       "current": {
         "last_updated_epoch":1551474907,
         "last_updated":"2019-03-01 22:15",
         "temp_c":10.0,
         "temp_f":50.0,
         "is_day":0,
         "condition": {
           "text":"Partly cloudy",
           "icon":"//cdn.apixu.com/weather/64x64/night/116.png",
           "code":1003
         },
         "wind_mph":5.6,
         "wind_kph":9.0,
         "wind_degree":240,
         "wind_dir":"WSW",
         "pressure_mb":1021.0,
         "pressure_in":30.6,
         "precip_mm":0.2,
         "precip_in":0.01,
         "humidity":82,
         "cloud":75,
         "feelslike_c":8.8,
         "feelslike_f":47.9,
         "vis_km":10.0,
         "vis_miles":6.0,
         "uv":0.0
       }
     }
"""
