Micro benchmarks that demonstrate pollution issues with various different
flavours of Unsafe memory accessors.

Run as follows:
----

    $ export JAVA_HOME=/Users/chegar/binaries/jdk-16.jdk/Contents/Home/; mvn clean install

    $ /Users/chegar/binaries/jdk-16.jdk/Contents/Home/bin/java -jar target/benchmarks.jar

Results:
----

(scroll to end for benchmark results)

    $ sw_vers
    ProductName:	Mac OS X
    ProductVersion:	10.14.6
    BuildVersion:	18G8022

    $ uname -a
    Darwin chhegar-MBP.local 18.7.0 Darwin Kernel Version 18.7.0: Tue Jan 12 22:04:47 PST 2021; root:xnu-4903.278.56~1/RELEASE_X86_64 x86_64

    $ sysctl -n machdep.cpu.brand_string
    Intel(R) Core(TM) i7-4578U CPU @ 3.00GHz

    $ system_profiler SPHardwareDataType
    Hardware:

        Hardware Overview:

          Model Name: MacBook Pro
          Model Identifier: MacBookPro11,1
          Processor Name: Intel Core i7
          Processor Speed: 3 GHz
          Number of Processors: 1
          Total Number of Cores: 2
          L2 Cache (per Core): 256 KB
          L3 Cache: 4 MB
          Hyper-Threading Technology: Enabled
          Memory: 16 GB
          Boot ROM Version: 430.0.0.0.0
          SMC Version (system): 2.16f68
          Serial Number (system): C02N839VG3QT
          Hardware UUID: D0C3DBA3-5DA5-53EC-9D10-D663BA155B26


    $ /Users/chegar/binaries/jdk-16.jdk/Contents/Home/bin/java -version
    openjdk version "16" 2021-03-16
    OpenJDK Runtime Environment (build 16+36-2231)
    OpenJDK 64-Bit Server VM (build 16+36-2231, mixed mode, sharing)


    $ /Users/chegar/binaries/jdk-16.jdk/Contents/Home/bin/java -jar target/benchmarks.jar
    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testDirect2Arg
    # Parameters: (pollute = false)

    # Run progress: 0.00% complete, ETA 00:03:00
    # Fork: 1 of 3
    # Warmup Iteration   1: 679.672 ns/op
    # Warmup Iteration   2: 670.311 ns/op
    # Warmup Iteration   3: 647.743 ns/op
    # Warmup Iteration   4: 645.012 ns/op
    # Warmup Iteration   5: 648.174 ns/op
    Iteration   1: 651.447 ns/op
    Iteration   2: 648.761 ns/op
    Iteration   3: 645.170 ns/op
    Iteration   4: 657.335 ns/op
    Iteration   5: 648.245 ns/op
    Iteration   6: 656.395 ns/op
    Iteration   7: 646.063 ns/op
    Iteration   8: 648.932 ns/op
    Iteration   9: 646.930 ns/op
    Iteration  10: 647.121 ns/op

    # Run progress: 4.17% complete, ETA 00:03:07
    # Fork: 2 of 3
    # Warmup Iteration   1: 678.346 ns/op
    # Warmup Iteration   2: 659.785 ns/op
    # Warmup Iteration   3: 652.145 ns/op
    # Warmup Iteration   4: 648.755 ns/op
    # Warmup Iteration   5: 654.042 ns/op
    Iteration   1: 649.098 ns/op
    Iteration   2: 647.168 ns/op
    Iteration   3: 805.224 ns/op
    Iteration   4: 677.133 ns/op
    Iteration   5: 705.290 ns/op
    Iteration   6: 887.445 ns/op
    Iteration   7: 672.823 ns/op
    Iteration   8: 661.240 ns/op
    Iteration   9: 663.898 ns/op
    Iteration  10: 652.946 ns/op

    # Run progress: 8.33% complete, ETA 00:02:55
    # Fork: 3 of 3
    # Warmup Iteration   1: 729.952 ns/op
    # Warmup Iteration   2: 665.914 ns/op
    # Warmup Iteration   3: 656.930 ns/op
    # Warmup Iteration   4: 659.720 ns/op
    # Warmup Iteration   5: 663.393 ns/op
    Iteration   1: 653.642 ns/op
    Iteration   2: 655.335 ns/op
    Iteration   3: 661.619 ns/op
    Iteration   4: 730.962 ns/op
    Iteration   5: 659.743 ns/op
    Iteration   6: 672.817 ns/op
    Iteration   7: 658.196 ns/op
    Iteration   8: 666.982 ns/op
    Iteration   9: 653.957 ns/op
    Iteration  10: 657.010 ns/op


    Result "p.BufferUnsafeBench.testDirect2Arg":
      672.964 ±(99.9%) 34.478 ns/op [Average]
      (min, avg, max) = (645.170, 672.964, 887.445), stdev = 51.606
      CI (99.9%): [638.486, 707.443] (assumes normal distribution)


    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testDirect2Arg
    # Parameters: (pollute = true)

    # Run progress: 12.50% complete, ETA 00:02:47
    # Fork: 1 of 3
    # Warmup Iteration   1: 2570.945 ns/op
    # Warmup Iteration   2: 2550.771 ns/op
    # Warmup Iteration   3: 2570.011 ns/op
    # Warmup Iteration   4: 2517.394 ns/op
    # Warmup Iteration   5: 2522.043 ns/op
    Iteration   1: 2494.125 ns/op
    Iteration   2: 2501.838 ns/op
    Iteration   3: 2484.975 ns/op
    Iteration   4: 2709.874 ns/op
    Iteration   5: 2475.571 ns/op
    Iteration   6: 2473.081 ns/op
    Iteration   7: 2720.208 ns/op
    Iteration   8: 2601.569 ns/op
    Iteration   9: 2522.954 ns/op
    Iteration  10: 2469.280 ns/op

    # Run progress: 16.67% complete, ETA 00:02:38
    # Fork: 2 of 3
    # Warmup Iteration   1: 2661.147 ns/op
    # Warmup Iteration   2: 2591.682 ns/op
    # Warmup Iteration   3: 2510.993 ns/op
    # Warmup Iteration   4: 2550.919 ns/op
    # Warmup Iteration   5: 2475.950 ns/op
    Iteration   1: 2490.212 ns/op
    Iteration   2: 2504.675 ns/op
    Iteration   3: 2494.824 ns/op
    Iteration   4: 2475.703 ns/op
    Iteration   5: 2481.591 ns/op
    Iteration   6: 2504.815 ns/op
    Iteration   7: 2555.828 ns/op
    Iteration   8: 2487.473 ns/op
    Iteration   9: 2489.856 ns/op
    Iteration  10: 2475.874 ns/op

    # Run progress: 20.83% complete, ETA 00:02:30
    # Fork: 3 of 3
    # Warmup Iteration   1: 2548.855 ns/op
    # Warmup Iteration   2: 2563.036 ns/op
    # Warmup Iteration   3: 2510.802 ns/op
    # Warmup Iteration   4: 2467.721 ns/op
    # Warmup Iteration   5: 2478.703 ns/op
    Iteration   1: 2511.376 ns/op
    Iteration   2: 2483.633 ns/op
    Iteration   3: 2473.841 ns/op
    Iteration   4: 2477.012 ns/op
    Iteration   5: 2465.110 ns/op
    Iteration   6: 2508.874 ns/op
    Iteration   7: 2475.607 ns/op
    Iteration   8: 2485.482 ns/op
    Iteration   9: 2470.835 ns/op
    Iteration  10: 2520.717 ns/op


    Result "p.BufferUnsafeBench.testDirect2Arg":
      2509.560 ±(99.9%) 41.745 ns/op [Average]
      (min, avg, max) = (2465.110, 2509.560, 2720.208), stdev = 62.482
      CI (99.9%): [2467.815, 2551.305] (assumes normal distribution)


    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testDirect3Arg
    # Parameters: (pollute = false)

    # Run progress: 25.00% complete, ETA 00:02:22
    # Fork: 1 of 3
    # Warmup Iteration   1: 678.005 ns/op
    # Warmup Iteration   2: 667.370 ns/op
    # Warmup Iteration   3: 659.657 ns/op
    # Warmup Iteration   4: 647.226 ns/op
    # Warmup Iteration   5: 651.257 ns/op
    Iteration   1: 662.682 ns/op
    Iteration   2: 648.955 ns/op
    Iteration   3: 650.708 ns/op
    Iteration   4: 648.604 ns/op
    Iteration   5: 694.576 ns/op
    Iteration   6: 647.807 ns/op
    Iteration   7: 646.897 ns/op
    Iteration   8: 652.461 ns/op
    Iteration   9: 650.792 ns/op
    Iteration  10: 648.271 ns/op

    # Run progress: 29.17% complete, ETA 00:02:14
    # Fork: 2 of 3
    # Warmup Iteration   1: 668.690 ns/op
    # Warmup Iteration   2: 664.132 ns/op
    # Warmup Iteration   3: 655.102 ns/op
    # Warmup Iteration   4: 658.052 ns/op
    # Warmup Iteration   5: 651.348 ns/op
    Iteration   1: 657.413 ns/op
    Iteration   2: 648.911 ns/op
    Iteration   3: 647.561 ns/op
    Iteration   4: 656.758 ns/op
    Iteration   5: 657.183 ns/op
    Iteration   6: 647.945 ns/op
    Iteration   7: 646.672 ns/op
    Iteration   8: 647.444 ns/op
    Iteration   9: 646.799 ns/op
    Iteration  10: 647.574 ns/op

    # Run progress: 33.33% complete, ETA 00:02:06
    # Fork: 3 of 3
    # Warmup Iteration   1: 677.149 ns/op
    # Warmup Iteration   2: 667.338 ns/op
    # Warmup Iteration   3: 650.856 ns/op
    # Warmup Iteration   4: 658.935 ns/op
    # Warmup Iteration   5: 797.333 ns/op
    Iteration   1: 705.678 ns/op
    Iteration   2: 698.474 ns/op
    Iteration   3: 875.251 ns/op
    Iteration   4: 654.188 ns/op
    Iteration   5: 650.233 ns/op
    Iteration   6: 662.756 ns/op
    Iteration   7: 650.000 ns/op
    Iteration   8: 660.396 ns/op
    Iteration   9: 650.611 ns/op
    Iteration  10: 648.805 ns/op


    Result "p.BufferUnsafeBench.testDirect3Arg":
      663.747 ±(99.9%) 28.611 ns/op [Average]
      (min, avg, max) = (646.672, 663.747, 875.251), stdev = 42.823
      CI (99.9%): [635.136, 692.358] (assumes normal distribution)


    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testDirect3Arg
    # Parameters: (pollute = true)

    # Run progress: 37.50% complete, ETA 00:01:58
    # Fork: 1 of 3
    # Warmup Iteration   1: 2671.059 ns/op
    # Warmup Iteration   2: 2561.269 ns/op
    # Warmup Iteration   3: 2486.251 ns/op
    # Warmup Iteration   4: 2491.837 ns/op
    # Warmup Iteration   5: 2529.855 ns/op
    Iteration   1: 2513.040 ns/op
    Iteration   2: 2527.658 ns/op
    Iteration   3: 2475.433 ns/op
    Iteration   4: 2475.062 ns/op
    Iteration   5: 2512.341 ns/op
    Iteration   6: 2529.348 ns/op
    Iteration   7: 2529.219 ns/op
    Iteration   8: 2504.315 ns/op
    Iteration   9: 2506.195 ns/op
    Iteration  10: 2514.517 ns/op

    # Run progress: 41.67% complete, ETA 00:01:50
    # Fork: 2 of 3
    # Warmup Iteration   1: 2543.457 ns/op
    # Warmup Iteration   2: 2522.065 ns/op
    # Warmup Iteration   3: 2481.437 ns/op
    # Warmup Iteration   4: 2474.650 ns/op
    # Warmup Iteration   5: 2482.633 ns/op
    Iteration   1: 2481.198 ns/op
    Iteration   2: 2467.915 ns/op
    Iteration   3: 2471.248 ns/op
    Iteration   4: 2458.294 ns/op
    Iteration   5: 2476.839 ns/op
    Iteration   6: 2479.969 ns/op
    Iteration   7: 2471.686 ns/op
    Iteration   8: 2571.060 ns/op
    Iteration   9: 2511.420 ns/op
    Iteration  10: 2494.662 ns/op

    # Run progress: 45.83% complete, ETA 00:01:42
    # Fork: 3 of 3
    # Warmup Iteration   1: 2546.260 ns/op
    # Warmup Iteration   2: 2533.680 ns/op
    # Warmup Iteration   3: 2479.298 ns/op
    # Warmup Iteration   4: 2472.586 ns/op
    # Warmup Iteration   5: 2483.080 ns/op
    Iteration   1: 2468.345 ns/op
    Iteration   2: 2549.151 ns/op
    Iteration   3: 2542.727 ns/op
    Iteration   4: 2468.270 ns/op
    Iteration   5: 2474.004 ns/op
    Iteration   6: 2482.845 ns/op
    Iteration   7: 2479.132 ns/op
    Iteration   8: 2501.424 ns/op
    Iteration   9: 2501.660 ns/op
    Iteration  10: 2467.836 ns/op


    Result "p.BufferUnsafeBench.testDirect3Arg":
      2496.894 ±(99.9%) 19.058 ns/op [Average]
      (min, avg, max) = (2458.294, 2496.894, 2571.060), stdev = 28.526
      CI (99.9%): [2477.835, 2515.952] (assumes normal distribution)


    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testOnHeap2Arg
    # Parameters: (pollute = false)

    # Run progress: 50.00% complete, ETA 00:01:34
    # Fork: 1 of 3
    # Warmup Iteration   1: 890.508 ns/op
    # Warmup Iteration   2: 841.081 ns/op
    # Warmup Iteration   3: 833.068 ns/op
    # Warmup Iteration   4: 832.315 ns/op
    # Warmup Iteration   5: 832.424 ns/op
    Iteration   1: 832.816 ns/op
    Iteration   2: 833.110 ns/op
    Iteration   3: 837.817 ns/op
    Iteration   4: 840.024 ns/op
    Iteration   5: 840.313 ns/op
    Iteration   6: 851.532 ns/op
    Iteration   7: 837.144 ns/op
    Iteration   8: 847.836 ns/op
    Iteration   9: 833.337 ns/op
    Iteration  10: 832.691 ns/op

    # Run progress: 54.17% complete, ETA 00:01:26
    # Fork: 2 of 3
    # Warmup Iteration   1: 921.844 ns/op
    # Warmup Iteration   2: 835.419 ns/op
    # Warmup Iteration   3: 835.660 ns/op
    # Warmup Iteration   4: 850.285 ns/op
    # Warmup Iteration   5: 842.113 ns/op
    Iteration   1: 837.656 ns/op
    Iteration   2: 837.715 ns/op
    Iteration   3: 830.253 ns/op
    Iteration   4: 839.616 ns/op
    Iteration   5: 835.785 ns/op
    Iteration   6: 847.678 ns/op
    Iteration   7: 833.273 ns/op
    Iteration   8: 856.335 ns/op
    Iteration   9: 838.919 ns/op
    Iteration  10: 845.976 ns/op

    # Run progress: 58.33% complete, ETA 00:01:18
    # Fork: 3 of 3
    # Warmup Iteration   1: 884.979 ns/op
    # Warmup Iteration   2: 836.439 ns/op
    # Warmup Iteration   3: 834.413 ns/op
    # Warmup Iteration   4: 839.940 ns/op
    # Warmup Iteration   5: 836.704 ns/op
    Iteration   1: 829.581 ns/op
    Iteration   2: 833.467 ns/op
    Iteration   3: 837.470 ns/op
    Iteration   4: 837.989 ns/op
    Iteration   5: 837.574 ns/op
    Iteration   6: 829.247 ns/op
    Iteration   7: 853.205 ns/op
    Iteration   8: 831.892 ns/op
    Iteration   9: 834.266 ns/op
    Iteration  10: 830.892 ns/op


    Result "p.BufferUnsafeBench.testOnHeap2Arg":
      838.180 ±(99.9%) 4.766 ns/op [Average]
      (min, avg, max) = (829.247, 838.180, 856.335), stdev = 7.133
      CI (99.9%): [833.414, 842.946] (assumes normal distribution)


    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testOnHeap2Arg
    # Parameters: (pollute = true)

    # Run progress: 62.50% complete, ETA 00:01:10
    # Fork: 1 of 3
    # Warmup Iteration   1: 3198.364 ns/op
    # Warmup Iteration   2: 3120.252 ns/op
    # Warmup Iteration   3: 3987.650 ns/op
    # Warmup Iteration   4: 3106.141 ns/op
    # Warmup Iteration   5: 3238.778 ns/op
    Iteration   1: 3954.403 ns/op
    Iteration   2: 3113.352 ns/op
    Iteration   3: 3020.153 ns/op
    Iteration   4: 2990.420 ns/op
    Iteration   5: 2990.890 ns/op
    Iteration   6: 2989.008 ns/op
    Iteration   7: 2985.917 ns/op
    Iteration   8: 3001.576 ns/op
    Iteration   9: 2989.534 ns/op
    Iteration  10: 3026.602 ns/op

    # Run progress: 66.67% complete, ETA 00:01:02
    # Fork: 2 of 3
    # Warmup Iteration   1: 3098.414 ns/op
    # Warmup Iteration   2: 3101.452 ns/op
    # Warmup Iteration   3: 3018.096 ns/op
    # Warmup Iteration   4: 2996.364 ns/op
    # Warmup Iteration   5: 2988.187 ns/op
    Iteration   1: 3007.169 ns/op
    Iteration   2: 2995.930 ns/op
    Iteration   3: 2990.258 ns/op
    Iteration   4: 3033.964 ns/op
    Iteration   5: 2984.591 ns/op
    Iteration   6: 2999.415 ns/op
    Iteration   7: 3000.422 ns/op
    Iteration   8: 2984.044 ns/op
    Iteration   9: 2983.806 ns/op
    Iteration  10: 2988.639 ns/op

    # Run progress: 70.83% complete, ETA 00:00:54
    # Fork: 3 of 3
    # Warmup Iteration   1: 3147.728 ns/op
    # Warmup Iteration   2: 3130.269 ns/op
    # Warmup Iteration   3: 2981.215 ns/op
    # Warmup Iteration   4: 3037.248 ns/op
    # Warmup Iteration   5: 2989.182 ns/op
    Iteration   1: 2989.875 ns/op
    Iteration   2: 2990.335 ns/op
    Iteration   3: 2990.922 ns/op
    Iteration   4: 3001.541 ns/op
    Iteration   5: 3000.115 ns/op
    Iteration   6: 2986.130 ns/op
    Iteration   7: 2985.713 ns/op
    Iteration   8: 2992.921 ns/op
    Iteration   9: 2986.436 ns/op
    Iteration  10: 3042.344 ns/op


    Result "p.BufferUnsafeBench.testOnHeap2Arg":
      3033.214 ±(99.9%) 117.520 ns/op [Average]
      (min, avg, max) = (2983.806, 3033.214, 3954.403), stdev = 175.898
      CI (99.9%): [2915.694, 3150.734] (assumes normal distribution)


    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testOnHeap3Arg
    # Parameters: (pollute = false)

    # Run progress: 75.00% complete, ETA 00:00:47
    # Fork: 1 of 3
    # Warmup Iteration   1: 891.244 ns/op
    # Warmup Iteration   2: 839.132 ns/op
    # Warmup Iteration   3: 834.516 ns/op
    # Warmup Iteration   4: 830.279 ns/op
    # Warmup Iteration   5: 834.433 ns/op
    Iteration   1: 830.567 ns/op
    Iteration   2: 854.498 ns/op
    Iteration   3: 834.583 ns/op
    Iteration   4: 836.194 ns/op
    Iteration   5: 838.081 ns/op
    Iteration   6: 839.256 ns/op
    Iteration   7: 838.584 ns/op
    Iteration   8: 832.240 ns/op
    Iteration   9: 833.045 ns/op
    Iteration  10: 841.361 ns/op

    # Run progress: 79.17% complete, ETA 00:00:39
    # Fork: 2 of 3
    # Warmup Iteration   1: 974.737 ns/op
    # Warmup Iteration   2: 988.315 ns/op
    # Warmup Iteration   3: 884.903 ns/op
    # Warmup Iteration   4: 842.851 ns/op
    # Warmup Iteration   5: 849.743 ns/op
    Iteration   1: 887.657 ns/op
    Iteration   2: 871.703 ns/op
    Iteration   3: 845.420 ns/op
    Iteration   4: 1020.708 ns/op
    Iteration   5: 839.112 ns/op
    Iteration   6: 835.463 ns/op
    Iteration   7: 838.492 ns/op
    Iteration   8: 850.158 ns/op
    Iteration   9: 836.531 ns/op
    Iteration  10: 830.181 ns/op

    # Run progress: 83.33% complete, ETA 00:00:31
    # Fork: 3 of 3
    # Warmup Iteration   1: 908.878 ns/op
    # Warmup Iteration   2: 847.972 ns/op
    # Warmup Iteration   3: 841.904 ns/op
    # Warmup Iteration   4: 839.273 ns/op
    # Warmup Iteration   5: 837.908 ns/op
    Iteration   1: 834.802 ns/op
    Iteration   2: 829.276 ns/op
    Iteration   3: 847.549 ns/op
    Iteration   4: 833.348 ns/op
    Iteration   5: 839.387 ns/op
    Iteration   6: 845.301 ns/op
    Iteration   7: 833.861 ns/op
    Iteration   8: 830.667 ns/op
    Iteration   9: 830.669 ns/op
    Iteration  10: 837.088 ns/op


    Result "p.BufferUnsafeBench.testOnHeap3Arg":
      846.526 ±(99.9%) 23.497 ns/op [Average]
      (min, avg, max) = (829.276, 846.526, 1020.708), stdev = 35.170
      CI (99.9%): [823.029, 870.023] (assumes normal distribution)


    # JMH version: 1.24-SNAPSHOT
    # VM version: JDK 16, OpenJDK 64-Bit Server VM, 16+36-2231
    # VM invoker: /Users/chhegar/binaries/jdk-16.jdk/Contents/Home/bin/java
    # VM options: --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
    # Warmup: 5 iterations, 500 ms each
    # Measurement: 10 iterations, 500 ms each
    # Timeout: 10 min per iteration
    # Threads: 1 thread, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: p.BufferUnsafeBench.testOnHeap3Arg
    # Parameters: (pollute = true)

    # Run progress: 87.50% complete, ETA 00:00:23
    # Fork: 1 of 3
    # Warmup Iteration   1: 3098.597 ns/op
    # Warmup Iteration   2: 3072.293 ns/op
    # Warmup Iteration   3: 2977.512 ns/op
    # Warmup Iteration   4: 2981.278 ns/op
    # Warmup Iteration   5: 2984.675 ns/op
    Iteration   1: 2989.732 ns/op
    Iteration   2: 2993.025 ns/op
    Iteration   3: 3122.003 ns/op
    Iteration   4: 2990.514 ns/op
    Iteration   5: 2989.393 ns/op
    Iteration   6: 2994.801 ns/op
    Iteration   7: 2989.153 ns/op
    Iteration   8: 2978.048 ns/op
    Iteration   9: 3010.310 ns/op
    Iteration  10: 2995.482 ns/op

    # Run progress: 91.67% complete, ETA 00:00:15
    # Fork: 2 of 3
    # Warmup Iteration   1: 3090.284 ns/op
    # Warmup Iteration   2: 3081.552 ns/op
    # Warmup Iteration   3: 3000.481 ns/op
    # Warmup Iteration   4: 2989.972 ns/op
    # Warmup Iteration   5: 3123.482 ns/op
    Iteration   1: 3393.960 ns/op
    Iteration   2: 3461.881 ns/op
    Iteration   3: 3199.872 ns/op
    Iteration   4: 3188.761 ns/op
    Iteration   5: 3003.868 ns/op
    Iteration   6: 3170.174 ns/op
    Iteration   7: 3103.230 ns/op
    Iteration   8: 3975.251 ns/op
    Iteration   9: 5151.540 ns/op
    Iteration  10: 3551.804 ns/op

    # Run progress: 95.83% complete, ETA 00:00:07
    # Fork: 3 of 3
    # Warmup Iteration   1: 3238.881 ns/op
    # Warmup Iteration   2: 3066.491 ns/op
    # Warmup Iteration   3: 3010.468 ns/op
    # Warmup Iteration   4: 3004.574 ns/op
    # Warmup Iteration   5: 2997.447 ns/op
    Iteration   1: 2980.235 ns/op
    Iteration   2: 3005.488 ns/op
    Iteration   3: 2982.562 ns/op
    Iteration   4: 2991.711 ns/op
    Iteration   5: 3027.631 ns/op
    Iteration   6: 3001.436 ns/op
    Iteration   7: 3033.919 ns/op
    Iteration   8: 3004.456 ns/op
    Iteration   9: 3022.601 ns/op
    Iteration  10: 3025.130 ns/op


    Result "p.BufferUnsafeBench.testOnHeap3Arg":
      3177.599 ±(99.9%) 289.424 ns/op [Average]
      (min, avg, max) = (2978.048, 3177.599, 5151.540), stdev = 433.196
      CI (99.9%): [2888.175, 3467.023] (assumes normal distribution)


    # Run complete. Total time: 00:03:08

    REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
    why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
    experiments, perform baseline and negative tests that provide experimental control, make sure
    the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
    Do not assume the numbers tell you what you want them to tell.

    Benchmark                         (pollute)  Mode  Cnt     Score     Error  Units
    BufferUnsafeBench.testDirect2Arg      false  avgt   30   672.964 ±  34.478  ns/op
    BufferUnsafeBench.testDirect2Arg       true  avgt   30  2509.560 ±  41.745  ns/op
    BufferUnsafeBench.testDirect3Arg      false  avgt   30   663.747 ±  28.611  ns/op
    BufferUnsafeBench.testDirect3Arg       true  avgt   30  2496.894 ±  19.058  ns/op
    BufferUnsafeBench.testOnHeap2Arg      false  avgt   30   838.180 ±   4.766  ns/op
    BufferUnsafeBench.testOnHeap2Arg       true  avgt   30  3033.214 ± 117.520  ns/op
    BufferUnsafeBench.testOnHeap3Arg      false  avgt   30   846.526 ±  23.497  ns/op
    BufferUnsafeBench.testOnHeap3Arg       true  avgt   30  3177.599 ± 289.424  ns/op
