# Fake SMTP Server
This jar starts up a fake SMTP server for use during development. Without it running, or without a valid SMTP server configured
the application will encounter exceptions during email sending.

To simulate email sending, start this jar using the following command

```
    java -jar fakeSMTP-2.0.jar
```

Additionally, you can also avoid these exceptions by disabling sending of emails by setting the following property inside the properties.yml file to false:

```
    ebookstore:
        mail:
            enabled: false
```

