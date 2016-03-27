SlickNode
=========


A reactive wrapper around Android's Google Play Services Wear API in Kotlin.

Usage
-----

Example to send a message to all nodes with a specific capacity:


```Kotlin
CapabilityObservable.getInfoReachable(googleApiClient, capability)
                .flatMapIterable { it -> it.nodes }
                .map { it.id }
                .flatMap { MessageObservable.sendMessage(googleApiClient, it, "/message-path", ByteArray(0)) }
                .subscribe()
```

The library contains the following observables which correspond to the relevant classes in traditional
Google Api Services:

Capability Api:
 
 - CapabilitiesListenerObservable
 - CapabilityListenerObservable
 - CapabilityObservable
 
Data Api:
 
 - DataItemObservable
 - DataListenerObservable
 
Messages Api:
 
 - MessageListenerObservable
 - MessageObservable
 
Node Api:
 
 - NodeObservable
 
 
### Known issues


The ChannelApi is not yet backported.


### Version/Usage

Gradle:

```gradle
compile 'com.lorentzos.slicknode:slicknode-kotlin:X.Y.Z.PATCH'
```

Where "X.Y.Z" are the same version with Google Play Services (e.g. 8.4.1) and ".PATCH" the version:
e.g. `compile 'com.lorentzos.slicknode:slicknode-kotlin:8.4.1.1'`



License
--------

    Copyright 2016 Dionysis Lorentzos

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
