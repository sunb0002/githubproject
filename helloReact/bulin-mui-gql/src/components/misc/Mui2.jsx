import AccountCircle from "@mui/icons-material/AccountCircle";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import SnowboardingOutlinedIcon from "@mui/icons-material/SnowboardingOutlined";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import {
    Avatar,
    Button,
    Container,
    FormControl,
    FormControlLabel,
    FormHelperText,
    Grid,
    Input,
    InputAdornment,
    InputLabel,
    Link,
    TextField,
    Typography,
} from "@mui/material";
import Checkbox from "@mui/material/Checkbox";
import { Box } from "@mui/system";
import React from "react";
import { useState } from "react";

export const Mui2 = () => {
    return (
        <Container maxWidth="xs">
            <Banner />
            <Form />
            <CopyRight sx={{ fontStyle: "italic", mt: 2 }} />
        </Container>
    );
};

const Banner = () => (
    <Box sx={{ outline: "1px dashed gray" }}>
        <Avatar sx={{ m: "auto", bgcolor: "success.main" }}>
            <LockOutlinedIcon />
        </Avatar>
        <Typography variant="h5">Sign Up</Typography>
    </Box>
);

const Form = () => {
    const [info, setInfo] = useState({
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        showPwd: false,
    });
    const isValidEmail = (email) => email.length > 2;
    const handleChange = (prop) => (e) =>
        setInfo({ ...info, [prop]: e.target.value });
    const handleShowPwd = () => setInfo({ ...info, showPwd: !info.showPwd });

    return (
        <Box sx={{ mt: 2 }}>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <TextField
                        autoFocus
                        required
                        autoComplete="off"
                        label="First Name"
                        name="first-name"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        required
                        autoComplete="off"
                        label="Last Name"
                        name="last-name"
                    />
                </Grid>
                <Grid item xs={12}>
                    <FormControl
                        error={!isValidEmail(info.email)}
                        fullWidth
                        variant="standard"
                    >
                        <InputLabel>Your Email Address</InputLabel>
                        <Input
                            id="email-address"
                            autoComplete="off"
                            onChange={handleChange("email")}
                            startAdornment={
                                <InputAdornment position="start">
                                    <AccountCircle />
                                </InputAdornment>
                            }
                        />
                        {!isValidEmail(info.email) && (
                            <FormHelperText error>Invalid email</FormHelperText>
                        )}
                    </FormControl>
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        required
                        id="password"
                        type={info.showPwd ? "text" : "password"}
                        autoComplete="off"
                        label="Password"
                        InputLabelProps={{ style: { color: "blue" } }}
                        onChange={handleChange("password")}
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <SnowboardingOutlinedIcon />
                                </InputAdornment>
                            ),
                            endAdornment: (
                                <InputAdornment
                                    position="end"
                                    onClick={handleShowPwd}
                                >
                                    {info.showPwd ? (
                                        <Visibility />
                                    ) : (
                                        <VisibilityOff />
                                    )}
                                </InputAdornment>
                            ),
                        }}
                    ></TextField>
                </Grid>
                <Grid item xs={12}>
                    <FormControlLabel
                        control={<Checkbox value="agreed" color="primary" />}
                        label={
                            <Typography sx={{ textAlign: "left" }}>
                                I want to receive coins, oil and diamonds via
                                email.
                            </Typography>
                        }
                    />
                </Grid>
                <Grid item xs={12}>
                    {"Already have a Bulin? "}
                    <Link href="#" underline="hover">
                        Sign In
                    </Link>
                </Grid>
                <Grid item xs={12}>
                    <Button fullWidth variant="contained">
                        Sign Up Now
                    </Button>
                </Grid>
            </Grid>
        </Box>
    );
};

const CopyRight = (props) => (
    <Typography
        variant="body2"
        align="center"
        color="text.secondary"
        {...props}
    >
        CopyRight @
        <Link color="info.main" href="https://mui.com/components/">
            AzurLane Website
        </Link>{" "}
        {new Date().getFullYear()}
    </Typography>
);
