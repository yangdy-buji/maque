package com.maya.maque.config;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.reload.ReloadStrategy;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfiguration {

  @Value("${configRepoPath:https://github.com/yangdy-buji/configuration.git}")
  private String configRepoPath; // Run with -DconfigRepoPath=<repositoryUrl> parameter to override

  @Value("${configBranch:master}")
  private String branch; // Run with -DconfigBranch=<branchName> parameter to override

  @Value("${gitUser:yangdy-buji}")
  private String gitUser;
  @Value("${gitPassword:crazy_rain@2020}")
  private String gitPassword;

  @Bean
  public ConfigurationProvider configurationProvider() {
    // Use Git repository as configuration store
    ConfigurationSource source = new GitConfigurationSourceBuilder()
        .withRepositoryURI(configRepoPath)
        .withCredentials(gitUser,gitPassword)
        .build();

    // Select branch to use (use new DefaultEnvironment()) for master
    Environment environment = new ImmutableEnvironment(branch);

    // Reload configuration every 5 seconds
    ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(5, TimeUnit.SECONDS);

    // Create provider
    return new ConfigurationProviderBuilder()
        .withConfigurationSource(source)
        .withEnvironment(environment)
        .withReloadStrategy(reloadStrategy)
        .build();
  }
}
