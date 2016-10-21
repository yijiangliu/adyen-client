#Java Client for Adyen Payment System

[Adyen](http://www.adyen.com)
> is the leading technology provider powering payments for global commerce in the 21st century.
> With a seamless solution for mobile, online and in-store transactions, our technology enables merchants to accept almost any
> type of payment, anywhere in the world.

Adyen Payment System, **APS**, lies at the heart of its payment platform and it's the service a merchant integrates with for
payment processing.

**adyen-client** consumes APS' JSON messaging based services and is built upon [adyen-api](https://github.com/woki/adyen-api).

##Usage

###Build it
```
$ git clone https://github.com/woki/adyen-client.git
$ cd adyen-client
$ mvn clean install spring-boot:repackage
```

###Use it
```
$ java -jar target/payments-adyen-client-${version}.jar -h
usage: com.github.woki.payments.adyen.client.Main
 -f,--request-file <arg>   the request file path
 -h,--help                 print this message
 -o,--orig-ref             replace request file's modificationRequest.originalReference
 -v,--value                replace request file's modificationRequest.modificationAmount
 -r,--merchref             replace request file's paymentRequest.reference / modificationRequest.reference
$
$ java -jar target/payments-adyen-client-${version}.jar -f my-auth-request.yaml

```

##Request file format - all possible fields for both Authorisation or Modification
For compelete description and usage of each field refer to Adyen's [API Playground](https://www.adyen.com/apidocs).
```yaml
type: authorization # authorization|authorization3d|cancel|cancelOrRefund|refund|capture
endpoint: https://pal-test.adyen.com
proxyConfig: pryusr:prxypass@prxysrvr:8888 # example, optional
credentials:
  username: ws@Company.TestCompany
  password: x30n%%
paymentRequest: # authorisations
  merchantAccount: TestMerchant
  reference: test-ref
  amount:
    currencyCode: USD
    value: 10000
  additionalAmount:
    currencyCode: USD
    value: 10000
  bankAccount:
    number: 10000-0
    bankLocationId: test-bank-location-id
    bankName: test-bank-name
    bic: test-bic
    countryCode: US
    iban: test-iban
    owner: test-owner
  billingAddress:
    countryCode: US
    city: test-city
    state: MA
    street: test-street
    postalCode: test-postal-code
    number: 100100
  browserInfo:
    accept: test-accept
    userAgent: test-user-agent
  captureDelayHours: 6
  dccQuote:
    account: test-dcc-account
    accountType: test-dcc-account-type
    baseAmount:
      currency: USD
      value: 0
    basePoints: 0
    buy:
      currency: USD
      value: 0
    interbank:
      currency: USD
      value: 0
    reference: test-dcc-reference
    sell:
      currency: USD
      value: 0
    signature: test-dcc-signature
    forexSource: test-forex-source
    forexType: test-forex-type
    validTill: 2015-12-30 00:00:00-0200 # format: yyyy-MM-dd HH:mm:ssZ
  deliveryAddress:
    countryCode: US
    city: test-city
    state: MA
    street: test-street
    postalCode: test-postal-code
    number: 100100
  deliveryDate: 2015-12-30 # format: yyyy-MM-dd
  deviceFingerprint: test-device-fingerprint
  fraudOffset: 0
  mcc: 0
  merchantOrderReference: test-merchant-order-reference
  mpiData:
    authenticationResponse: Y # Y, N, U, A
    cavv: test-cavv
    cavvAlgorithm: test-cavv-algorithm
    directoryResponse: Y # Y, N, U, E
    eci: test-eci
    xid: test-xid
  orderReference: test-order-reference
  recurring:
    contract: ONECLICK # ONECLICK, RECURRING, PAYOUT
    recurringDetailName: test-recurring-detail-name
  selectedBrand: test-selected-brand
  selectedRecurringDetailReference: test-selected-recurring-detail-reference
  sessionId: test-session-id
  md: test-md
  paResponse: test-pa-response
  card:
    number: 4111111111111111
    cvc: 123
    expiryMonth: 12
    expiryYear: 2016
    holder: Johnny Tester
  additionalData:
    additionalData.one: test-one
    additionalData.two: test-two
    additionalData.etc: test-etc
  installments: 1
  shopper:
    firstName: Johnny
    lastName: Tester
    email: johnny.tester@test.io
    ip: 127.0.0.1
    reference: test-shopper-reference
    interaction: ecommerce
    birth: 1986-12-30 # format: yyyy-MM-dd
    ssn: 1234567890
    phone: +55-12-3322-8957
    locale: en_US
    statement: test-statement
    
modificationRequest: # modifications (capture/cancel/refund/cancelOrRefund)
  merchantAccount: PayULatamMerchant
  reference: test-ref-0001
  originalReference: test-original-ref
  authorisationCode: test-auth-code
  modificationAmount:
      currencyCode: USD
      value: 100000
  additionalData:
    additionalData.one: test-one
    additionalData.two: test-two
    additionalData.etc: test-etc
```

##Request file format (typical)

###Authorization
```yaml
type: authorization
endpoint: https://pal-test.adyen.com
proxyConfig: pryusr:prxypass@prxysrvr:8888 # example, optional
credentials:
  username: ws@Company.TestCompany
  password: x30n%%
paymentRequest:
  merchantAccount: TestMerchant
  reference: test-ref
  amount:
    currencyCode: USD
    value: 10000
  billingAddress:
    countryCode: US
    city: test-city
    state: MA
    street: test-street
    postalCode: test-postal-code
    number: 100100
  browserInfo:
    accept: test-accept
    userAgent: test-user-agent
  deliveryAddress:
    countryCode: US
    city: test-city
    state: MA
    street: test-street
    postalCode: test-postal-code
    number: 100100
  merchantOrderReference: test-merchant-order-reference
  orderReference: test-order-reference
  card:
    number: 4111111111111111
    cvc: 123
    expiryMonth: 12
    expiryYear: 2016
    holder: Johnny Tester
  additionalData:
    additionalData.one: test-one
    additionalData.two: test-two
    additionalData.etc: test-etc
  shopper:
    firstName: Johnny
    lastName: Tester
    email: johnny.tester@test.io
    ip: 127.0.0.1
    reference: test-shopper-reference
    interaction: ecommerce
    birth: 1986-12-30 # format: yyyy-MM-dd
    ssn: 1234567890
    phone: +55-12-3322-8957
    locale: en_US
    statement: test-statement
```

###Capture
```yaml
type: capture
endpoint: https://pal-test.adyen.com
proxyConfig: pryusr:prxypass@prxysrvr:8888 # example, optional
credentials:
  username: ws@Company.TestCompany
  password: x30n%%
modificationRequest:
  merchantAccount: TestMerchant
  reference: test-ref
  originalReference: test-org-ref
```