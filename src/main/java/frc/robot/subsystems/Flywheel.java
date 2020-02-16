package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Flywheel extends SubsystemBase {
    Double exponent = 1.0;
    CANSparkMax left = new CANSparkMax(Constants.kFlyWheel_l, MotorType.kBrushless);
    CANSparkMax right = new CANSparkMax(Constants.kFlyWheel_R, MotorType.kBrushless);

    
    CANEncoder encoder;
    
    CANPIDController controller;

    Constants constants = Constants.getConstants();

    double setpoint = 0;

public Flywheel(){
    right.follow(left, true);
    right.set(0.0);
    
    left.setIdleMode(IdleMode.kCoast);
    right.setIdleMode(IdleMode.kCoast);
    
    encoder = right.getEncoder();
    controller = right.getPIDController();
    controller.setFeedbackDevice(encoder);
    stop();
    updateConstants();
}
//manual run of flywheel
    public void test(XboxController m_driver) {
        right.set(m_driver.getTriggerAxis(GenericHID.Hand.kLeft) * -1);
        
    }
    
    public void set(double setpoint) {
        controller.setReference(0, ControlType.kVelocity);
    }

    public void stop() {
        controller.setReference(0, ControlType.kDutyCycle);
    }

    public void update() {
    }

    public void updateConstants() {
        controller.setOutputRange(-1, 0);
        controller.setP(constants.shooterP);
        controller.setI(constants.shooterI);
        controller.setD(constants.shooterD);
        controller.setFF(constants.shooterF);
    }

    /**
     * Spins the flywheel
     */

    // exponential decay of speed
    public void SlowStop() {
        boolean finish = false;
        while (finish == false) {
            if (expont / expont > 0) {
                expont = 0.0;
                finish = true;
            } else if (expont > 0) {
                // adds exponital growth to speed
                expont = expont / expont;
                left.set(expont);
                right.set(-expont);
            } else {
                // for testing putting stop
                stop();
                finish = true;
            }

         }
        return expont;
    }
*/
    // exponential growth of speed
    public void SpeedUp() {
        exponent = .00001;
        boolean finish = false;
        left.set(exponent);
        
          while (finish == false) {
            if (exponent + exponent > Constants.kFlywheelSpeed) {
                exponent = Constants.kFlywheelSpeed;
                finish = true;
            } else if (expont > 0) {
                // adds exponital growth to speed
                expont = expont * expont;
                left.set(expont);
                right.set(-expont);
            } else {
                stop();
                finish = true;
            }

        }
}