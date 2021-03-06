# POI Matchers

[![Build Status](https://travis-ci.org/elizeuborges/poi-matchers.svg?branch=master)](https://travis-ci.org/elizeuborges/poi-matchers)
[![Coverage](https://codecov.io/github/elizeuborges/poi-matchers/coverage.svg?branch=master)](https://codecov.io/github/elizeuborges/poi-matchers?branch=master)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.github.elizeuborges:poi-matchers)](https://sonarqube.com/dashboard/index/com.github.elizeuborges%3Apoi-matchers)

> POI Matchers é uma biblioteca com Hamcrest Matcher para asserções em Workbook geradas com [Apache POI](https://poi.apache.org/)

## Quick Start

- Adicione ao seu projeto Maven a dependência para a POI Matchers (Verifique no [Repositório Maven](https://mvnrepository.com/artifact/com.github.elizeuborges/poi-matchers) a ultima versão):

```xml
<dependency>
  <groupId>com.github.elizeuborges</groupId>
  <artifactId>poi-matchers</artifactId>
  <version>1.0.0-beta</version>
  <scope>test</scope>
</dependency>
```

- Teste sua Workbook

```java
import static org.junit.Assert.assertThat;
import static com.github.elizeuborges.poimatchers.WorkbookMatcher.estaCom;

import org.apache.poi.ss.usermodel.Workbook;

import org.junit.Test;

public class POIWorkbookTest {

	@Test
	public void devePossuirUmJediNaPrimeiraCelula() {
		Workbook workbook = ... //gerar a planilha 
		//A1 representa a coordenada XY conforme documento xls 
		assertThat(workbook, estaCom("Skywalker").naCelula("A1"));
	}

}

```