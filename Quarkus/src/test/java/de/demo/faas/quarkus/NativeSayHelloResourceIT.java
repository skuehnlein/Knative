package de.demo.faas.quarkus;

import io.quarkus.test.junit.SubstrateTest;

@SubstrateTest
public class NativeSayHelloResourceIT extends SayHelloResourceTest {

    // Execute the same tests but in native mode.
}