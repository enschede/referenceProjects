# Onderscheppen van externe call bij integration test

Op keten van Spring componenten wordt een integratie test uitgevoerd.
Deze test kan niet het externe systeem aanroepen.

## Tijdens integration test
Component SmsSender roept HttpClient aan. HttpClient wordt gemockt.

IntegrationTestConfiguration definieert HttpClient als Mock, alleen in geval van een Integration test.
MockitoRestIntegrationTest::before definieert antwoord van mock.
MockitoRestIntegrationTest::testRestSender roept smsSender aan.

## Buiten integration test
Buiten testsituatie wordt smsSender gedefinieerd in ContextConfig class.
