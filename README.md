Crash line in [KotlinJsIrLink.kt](https://cs.android.com/android-studio/kotlin/+/master:libraries/tools/kotlin-gradle-plugin/src/main/kotlin/org/jetbrains/kotlin/gradle/targets/js/ir/KotlinJsIrLink.kt;l=62?q=KotlinJsIrLink.kt)

Log output

```> [...]
> Task :mymodule:jsJar UP-TO-DATE
> Task :mymodule:kspTestKotlinJs UP-TO-DATE
> Task :mymodule:compileTestKotlinJs UP-TO-DATE
> Task :mymodule:jsTestProcessResources NO-SOURCE
> Task :mymodule:jsTestClasses UP-TO-DATE
> Task :mymodule:compileTestDevelopmentExecutableKotlinJs FAILED

FAILURE: Build failed with an exception.

* What went wrong:
  Execution failed for task ':mymodule:compileTestDevelopmentExecutableKotlinJs'.
> Failed to calculate the value of task ':mymodule:compileTestDevelopmentExecutableKotlinJs' property 'entryModule$kotlin_gradle_plugin'.
> Collection has more than one element.

* Try:
  Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Exception is:
  org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':mymodule:compileTestDevelopmentExecutableKotlinJs'.
      at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:38)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:200)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:195)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$3.execute(DefaultBuildOperationRunner.java:75)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$3.execute(DefaultBuildOperationRunner.java:68)
      at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:153)
      at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:68)
      at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:62)
      at org.gradle.internal.operations.DefaultBuildOperationExecutor.lambda$call$2(DefaultBuildOperationExecutor.java:79)
      at org.gradle.internal.operations.UnmanagedBuildOperationWrapper.callWithUnmanagedSupport(UnmanagedBuildOperationWrapper.java:54)
      at org.gradle.internal.operations.DefaultBuildOperationExecutor.call(DefaultBuildOperationExecutor.java:79)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
      at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:74)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:408)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:395)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:388)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:374)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.lambda$run$0(DefaultPlanExecutor.java:127)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:191)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.executeNextNode(DefaultPlanExecutor.java:182)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:124)
      at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
      at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
      at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:56)
  Caused by: org.gradle.api.internal.provider.AbstractProperty$PropertyQueryException: Failed to calculate the value of task ':mymodule:compileTestDevelopmentExecutableKotlinJs' property 'entryModule$kotlin_gradle_plugin'.
      at org.gradle.api.internal.provider.AbstractProperty.finalizeNow(AbstractProperty.java:239)
      at org.gradle.api.internal.provider.AbstractProperty.beforeRead(AbstractProperty.java:230)
      at org.gradle.api.internal.provider.AbstractProperty.calculateOwnValue(AbstractProperty.java:126)
      at org.gradle.api.internal.provider.AbstractMinimalProvider.getOrNull(AbstractMinimalProvider.java:93)
      at org.gradle.api.internal.provider.ProviderResolutionStrategy$1.resolve(ProviderResolutionStrategy.java:27)
      at org.gradle.api.internal.file.collections.ProviderBackedFileCollection.visitChildren(ProviderBackedFileCollection.java:63)
      at org.gradle.api.internal.file.CompositeFileCollection.visitContents(CompositeFileCollection.java:119)
      at org.gradle.api.internal.file.AbstractFileCollection.visitStructure(AbstractFileCollection.java:330)
      at org.gradle.api.internal.file.CompositeFileCollection.lambda$visitContents$0(CompositeFileCollection.java:119)
      at org.gradle.api.internal.file.collections.UnpackingVisitor.add(UnpackingVisitor.java:74)
      at org.gradle.api.internal.file.collections.UnpackingVisitor.add(UnpackingVisitor.java:89)
      at org.gradle.api.internal.file.DefaultFileCollectionFactory$ResolvingFileCollection.visitChildren(DefaultFileCollectionFactory.java:333)
      at org.gradle.api.internal.file.CompositeFileCollection.visitContents(CompositeFileCollection.java:119)
      at org.gradle.api.internal.file.AbstractFileCollection.visitStructure(AbstractFileCollection.java:330)
      at org.gradle.api.internal.file.FileCollectionBackedFileTree.visitContents(FileCollectionBackedFileTree.java:98)
      at org.gradle.api.internal.file.FileCollectionBackedFileTree.visitContentsAsFileTrees(FileCollectionBackedFileTree.java:73)
      at org.gradle.api.internal.file.FileCollectionBackedFileTree.visit(FileCollectionBackedFileTree.java:67)
      at org.gradle.api.internal.file.AbstractFileTree.isEmpty(AbstractFileTree.java:63)
      at org.gradle.api.internal.file.CompositeFileCollection.isEmpty(CompositeFileCollection.java:61)
      at org.gradle.api.internal.file.CompositeFileCollection.isEmpty(CompositeFileCollection.java:61)
      at org.gradle.api.internal.tasks.execution.DefaultEmptySourceTaskSkipper.skipIfEmptySources(DefaultEmptySourceTaskSkipper.java:67)
      at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter$TaskExecution.skipIfInputsEmpty(ExecuteActionsTaskExecuter.java:451)
      at org.gradle.internal.execution.steps.SkipEmptyWorkStep.execute(SkipEmptyWorkStep.java:45)
      at org.gradle.internal.execution.steps.SkipEmptyWorkStep.execute(SkipEmptyWorkStep.java:32)
      at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsStartedStep.execute(MarkSnapshottingInputsStartedStep.java:38)
      at org.gradle.internal.execution.steps.LoadExecutionStateStep.execute(LoadExecutionStateStep.java:43)
      at org.gradle.internal.execution.steps.LoadExecutionStateStep.execute(LoadExecutionStateStep.java:31)
      at org.gradle.internal.execution.steps.AssignWorkspaceStep.lambda$execute$0(AssignWorkspaceStep.java:40)
      at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter$TaskExecution$2.withWorkspace(ExecuteActionsTaskExecuter.java:284)
      at org.gradle.internal.execution.steps.AssignWorkspaceStep.execute(AssignWorkspaceStep.java:40)
      at org.gradle.internal.execution.steps.AssignWorkspaceStep.execute(AssignWorkspaceStep.java:30)
      at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:37)
      at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:27)
      at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:44)
      at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:33)
      at org.gradle.internal.execution.impl.DefaultExecutionEngine$1.execute(DefaultExecutionEngine.java:76)
      at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:185)
      at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:174)
      at org.gradle.api.internal.tasks.execution.CleanupStaleOutputsExecuter.execute(CleanupStaleOutputsExecuter.java:109)
      at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
      at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
      at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
      at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:56)
      at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:200)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:195)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$3.execute(DefaultBuildOperationRunner.java:75)
      at org.gradle.internal.operations.DefaultBuildOperationRunner$3.execute(DefaultBuildOperationRunner.java:68)
      at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:153)
      at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:68)
      at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:62)
      at org.gradle.internal.operations.DefaultBuildOperationExecutor.lambda$call$2(DefaultBuildOperationExecutor.java:79)
      at org.gradle.internal.operations.UnmanagedBuildOperationWrapper.callWithUnmanagedSupport(UnmanagedBuildOperationWrapper.java:54)
      at org.gradle.internal.operations.DefaultBuildOperationExecutor.call(DefaultBuildOperationExecutor.java:79)
      at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
      at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:74)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:408)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:395)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:388)
      at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:374)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.lambda$run$0(DefaultPlanExecutor.java:127)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:191)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.executeNextNode(DefaultPlanExecutor.java:182)
      at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:124)
      at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
      at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
      at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:56)
  Caused by: java.lang.IllegalArgumentException: Collection has more than one element.
      at kotlin.collections.CollectionsKt___CollectionsKt.single(_Collections.kt:565)
      at org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrLink$Configurator$configure$1.invoke(KotlinJsIrLink.kt:54)
      at org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrLink$Configurator$configure$1.invoke(KotlinJsIrLink.kt:54)
      at org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrLink$sam$org_gradle_api_Transformer$0.transform(KotlinJsIrLink.kt)
      at org.gradle.api.internal.provider.TransformBackedProvider.mapValue(TransformBackedProvider.java:73)
      at org.gradle.api.internal.provider.TransformBackedProvider.calculateOwnValue(TransformBackedProvider.java:65)
      at org.gradle.api.internal.provider.AbstractMinimalProvider.calculateValue(AbstractMinimalProvider.java:103)
      at org.gradle.api.internal.provider.TransformBackedProvider.calculateOwnValue(TransformBackedProvider.java:64)
      at org.gradle.api.internal.provider.AbstractMinimalProvider.calculateValue(AbstractMinimalProvider.java:103)
      at org.gradle.api.internal.provider.MappingProvider.calculateOwnValue(MappingProvider.java:55)
      at org.gradle.api.internal.provider.AbstractMinimalProvider.calculateValue(AbstractMinimalProvider.java:103)
      at org.gradle.api.internal.provider.AbstractMinimalProvider.withFinalValue(AbstractMinimalProvider.java:151)
      at org.gradle.api.internal.provider.DefaultProperty.finalValue(DefaultProperty.java:133)
      at org.gradle.api.internal.provider.DefaultProperty.finalValue(DefaultProperty.java:25)
      at org.gradle.api.internal.provider.AbstractProperty.finalizeNow(AbstractProperty.java:236)
  ... 69 more

```